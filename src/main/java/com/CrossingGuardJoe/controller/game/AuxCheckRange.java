package com.CrossingGuardJoe.controller.game;

import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;

public class AuxCheckRange {
    public static boolean isInRangeJoeKid(Joe joe, Kid kid) {
        //1st x joe in rightside of kid, 2nd x joe in lefside of kid
        return joe.getPosition().getX() <= kid.getPosition().getX() + 9 &&
                joe.getPosition().getX() >= kid.getPosition().getX() - 20;
    }

    public static boolean isInRangeCarKid(Car car, Kid kid) {
        //1st x is leftside 2nd x is rightside
        return car.getPosition().getX() <= kid.getPosition().getX() + 118 &&
                car.getPosition().getX() >= kid.getPosition().getX() - 51 &&
                car.getPosition().getY() <= kid.getPosition().getY() + 100 &&
                car.getPosition().getY() >= kid.getPosition().getY() - 65;
    }
}