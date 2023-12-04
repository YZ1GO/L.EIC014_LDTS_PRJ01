package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.controller.game.GameController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.Game;

import java.util.*;

import static com.CrossingGuardJoe.controller.game.AuxCheckRange.isInRangeCarKid;
import static com.CrossingGuardJoe.controller.game.AuxCheckRange.isInRangeJoeKid;

public class KidController extends GameController {
    private static final int KID_STEP = 3;
    private static final double KID_SPEED = 0.005;
    private static final int MIN_KID_DISTANCE = 9;
    private static final int PASS_POINT = 80;
    private static final int MIN_Y_DISTANCE = 0;
    private static final int MAX_Y_DISTANCE = 500;
    private static final int Y_AFTER_HIT = 55;
    private long lastUpdateTime;
    private final Joe joe = getModel().getJoe();
    private Kid selectedKid;
    private final List<Kid> sentKids = new ArrayList<>();
    private int nextKidToMoveInQueueIndex;
    private boolean kidMovedInQueue = false;

    public KidController(Road road) {
        super(road);
        lastUpdateTime = System.currentTimeMillis();
    }

    public void moveKid(Kid kid) {
        KidAction(kid, new Position(kid.getPosition().getX() - KID_STEP, kid.getPosition().getY()), 'p');
    }

    public void moveKidAfterHit(Car car, Kid kid, int hitX, Iterator<Kid> kidIterator) {
        if (kid.getPosition().getY() > MAX_Y_DISTANCE) {
            kidIterator.remove();
        }
        kid.setPosition(new Position(hitX, car.getPosition().getY() + Y_AFTER_HIT));
    }

    public void stopKid(Kid kid) {
        KidAction(kid, kid.getPosition(), 's');
    }

    public void KidAction(Kid kid, Position position, char passOrStop) {
        if (passOrStop == 'p') {
            kid.setWalking();
            kid.setPosition(position);
        } else if (passOrStop == 's') {
            kid.setNotWalking();
        }
    }

    private boolean isFirstKid(Kid kid) {
        List<Kid> kids = getModel().getKids();
        return kids.indexOf(kid) == 0;
    }

    private boolean inMinDistance(Kid kid) {
        List<Kid> kids = getModel().getKids();
        if (!isFirstKid(kid)) {
            Kid kidInFront = kids.get(kids.indexOf(kid) - 1);
            if (!kidInFront.getIsHit()) {
                return kid.getPosition().getX() - kidInFront.getPosition().getX() <= MIN_KID_DISTANCE + 1;
            }
        }
        return false;
    }

    private boolean canContinueWalk(Kid kid) {
        if (!isFirstKid(kid) && inMinDistance(kid)) {
            stopKid(kid);
            return false;
        }
        return true;
    }

    private void repositionQueue() {
        List<Kid> kids = getModel().getKids();

        if (nextKidToMoveInQueueIndex < kids.size()) {
            Kid kidToMoveInQueue = kids.get(nextKidToMoveInQueueIndex);
            int movesLeft = kidToMoveInQueue.getMovesInQueueLeft();
            if (movesLeft > 0) {
                if (canContinueWalk(kidToMoveInQueue)) {
                    moveKid(kidToMoveInQueue);
                    kidMovedInQueue = true;
                }
                if (kidMovedInQueue) {
                    kidToMoveInQueue.addMovesInQueueLeft(-1);
                    kidMovedInQueue = false;
                }
            } else {
                stopKid(kidToMoveInQueue);
                nextKidToMoveInQueueIndex++;
                System.out.println(nextKidToMoveInQueueIndex);
            }
        }
    }

    @Override
    public void nextAction(Game game, GUI.ACTION action, long time) {
        List<Kid> kids = getModel().getKids();

        boolean joeInRange = false;

        for (Kid kid : kids) {
            if (isInRangeJoeKid(joe, kid) && kid.getPosition().getX() > PASS_POINT) {
                joeInRange = true;
                selectedKid = kid;
            }
            kid.setNotSelected();
        }

        if (joeInRange) {
            selectedKid.setSelected();
        }

        if (action == GUI.ACTION.DOWN && joeInRange) {
            selectedKid.setWalking();

            if (!sentKids.contains(selectedKid)) {
                sentKids.add(selectedKid);
                nextKidToMoveInQueueIndex = kids.indexOf(selectedKid) + 1;

                for (int i = nextKidToMoveInQueueIndex; i < kids.size(); i++) {
                    Kid kid = kids.get(i);
                    kid.addMovesInQueueLeft(3);
                }
            }
        }

        if (action == GUI.ACTION.UP && joeInRange) {
            selectedKid.setNotWalking();
        }

        if (time - lastUpdateTime > KID_SPEED && !kids.isEmpty()) {
            repositionQueue();
            for (Kid kid : sentKids) {
                if (kid.getWalkingState() && canContinueWalk(kid)) {
                    moveKid(kid);
                }
                if (isInRangeJoeKid(joe, kid) && joe.getIsRaisingStopSign() && kid.getPosition().getX() > PASS_POINT + 10) {
                    stopKid(kid);
                }
            }
            lastUpdateTime = time;
        }

        checkCollisions();
        checkPoints();
        checkLoss(); //temporary implemented
    }

    private void checkCollisions() {
        List<Car> cars = getModel().getCars();
        List<Kid> kids = getModel().getKids();
        int hitX;

        Iterator<Kid> kidIterator = kids.iterator();
        while (kidIterator.hasNext()) {
            Kid kid = kidIterator.next();
            for (Car car : cars) {
                if (isInRangeCarKid(car, kid)) {
                    hitX = kid.getPosition().getX();
                    kid.isHit();
                    kid.setNotWalking();
                    checkDeathCount(kid);
                    moveKidAfterHit(car, kid, hitX, kidIterator);
                }
            }
        }
    }

    private void checkDeathCount(Kid kid) {
        if (!kid.getDeathCounted()) {
            joe.removeHeart();
            kid.setDead();
        }
    }

    private void checkPoints() {
        Iterator<Kid> kidIterator = getModel().getKids().iterator();
        while (kidIterator.hasNext()) {
            Kid kid = kidIterator.next();
            if (kid.getPosition().getX() < MIN_Y_DISTANCE) {
                joe.addScore(kid.getPoints());
                kidIterator.remove();
            }
        }
    }

    private void checkLoss() {
        if (joe.getHearts() == 0) {
            //temporary
            System.out.println("loss");
        }
    }
}

