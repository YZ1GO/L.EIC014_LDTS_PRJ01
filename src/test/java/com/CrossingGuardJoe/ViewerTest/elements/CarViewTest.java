package com.CrossingGuardJoe.ViewerTest.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.viewer.game.elements.CarView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CarViewTest {

    Car carMock;
    GUI guiMock;
    Position positionMock;
    CarView carView;

    String[] CAR_image = new String[]{
            "                                    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                                    ",
            "                                    $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$                                    ",
            "                                   $$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$                                   ",
            "                                  $$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$                                   ",
            "                                  $$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$                                   ",
            "                                 $$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$                                  ",
            "                                 $$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$                                 ",
            "                                $$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$$                                ",
            "                                $@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@$$                               ",
            "                               $$@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@$                               ",
            "                               $@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@$$                              ",
            "                              $$~@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@~$                              ",
            "                              $~~@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@~~$                              ",
            "                             $~~~@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@~~~$                             ",
            "                            $$~~~@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@~~~~$                            ",
            "                            $~~~~@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$@@~~~~$$                           ",
            "                           $~~~~~@@$$@WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW@@$@@@~~~~~$                           ",
            "                          $$~~~~~~@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@@~~~~~~$                          ",
            "                          $~~~~~~~@$$~~~~~~~~~~~~~~~==============~~~~~~~~=====================~~~~~~==$@@~~~~~~~$$                         ",
            "                         $~~~~~~~~~$~~~~~~~~~~~~~~~==============~~~~~~~~=====================~~~~~~===$$@~~~~~~~~$$                        ",
            "                        $$~~~~~~~~~$~~~~~~~~~~~~~~==============~~~~~~~~~=====================~~~~~=====$~~~~~~~~~@$                        ",
            "                       $$@~~~~~~~~$$~~~~~~~~~~~~~==============~~~~~~~~~=====================~~~~~======$~~~~~~~~~@$$                       ",
            "                       $$@~~~~~~~$$~~~~~~~~~~~~~==============~~~~~~~~~=====================~~~~~======~$$~~~~~~~~@$$                       ",
            "                       $$@@~~~~~~$~~~~~~~~~~~~~===============~~~~~~~~=====================~~~~~~=====~~~$~~~~~~~~@$$$                      ",
            "                       $$$@~~~~~$$~~~~~~~~~~~~~==============~~~~~~~~=====================~~~~~~=====~~~~$$~~~~~~~@$@$                      ",
            "                      $@@$@~~~~~$~~~~~~~~~~~~~==============~~~~~~~~~====================~~~~~~=====~~~~~~$~~~~~~~@$@$                      ",
            "                      $@@$@~~~~~$~~~~~~~~~~~~==============~~~~~~~~~=====================~~~~~=====~~~~~~~$$~~~~~@@$@$                      ",
            "                      $@@$@~~~~$$~~~~~~~~~~~===============~~~~~~~~=====================~~~~~======~~~~~~~~$~~~~~@$$@$$                     ",
            "                     $$@@$@~~~$$~~~~~~~~~~~===============~~~~~~~~=====================~~~~~~=====~~~~~~~~~$$~~~~@$@@@$                     ",
            "                     $@@@$@~~~$$~~~~~~~~~~===============~~~~~~~~~====================~~~~~~=====~~~~~~~~~~~$~~~~@$@@@$                     ",
            "                     $@@@$@~~~$~~~~~~~~~~===============~~~~~~~~~=====================~~~~~======~~~~~~~~~~~$$~~~@$@@@$                     ",
            "             $$$$$   $@@@$@~~~$~~~~~~~~~===============~~~~~~~~~=====================~~~~~=====~~~~~~~~~~~~~~$~~~@$@@@$    $$$$$$$$         ",
            "           $$@@@@$$$$$@@@$@~~$$~~~~~~~~================~~~~~~~~======================~~~~======~~~~~~~~~~~~~~$$~~@$@@@$ $$$$@WWWW@$         ",
            "          $$@@@@@@@@$$@@@@$@~$~~~~~~~~================~~~~~~~~======================~~~~~=====~~~~~~~~~~~~~~~~$~@@$@@@@$$@WWWWWWW@$$        ",
            "          $@@@WWWWW@@$@@@@$@@$~~~~~~~=================~~~~~~~======================~~~~~=====~~~~~~~~~~~~~~~~~$$@$$@@@@$@WWWWW@@@@@$        ",
            "          $@WWWWWWWW@$@@@@$@$$~~~~~~~================~~~~~~~======================~~~~~======~~~~~~~~~~~~~~~~~~$@$@@@@$$WW@@@@@@@@@$        ",
            "          $WWWWW@@@@@$$@@@$$$~~~~~~=================~~~~~~~======================~~~~~======~~~~~~~~~~~~~~~~~~~$@$@@$$$@@@@@@@@@@@@$        ",
            "          $WW@@@@@@@@@$$@@@$$~~~~~~================~~~~~~~======================~~~~~======~~~~~~~~~~~~~~~~~~~~$$$$$$@@@@@@@@@@@@@@$        ",
            "          $W@@@@@@@@@@@$$@@$~~~~~~================~~~~~~~~=====================~~~~~======~~~~~~~~~~~~~~~~~~~~~~$$@@@@@@@@@@@@@@@@$$        ",
            "           $@@@@@@@@@@@@$$$$~~~~~$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$~~~~~$$@@@@@@@@@@@@@@@@$         ",
            "           $$$@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@$$         ",
            "             $$$@@@@@@@@@@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@@$$$$$@@@@@@@@@@@@@@$$          ",
            "                $$$$@@@@@@@$$$@@@@@@@@@@@$$$$$$@$$$$$$$$$$$@@@@@@@@@@@@@@@@@@@@@$$$$$$$$$@@@@@@@@@@@$$@@@@@@@@$$@@@@@@@@@@@@@$$$$           ",
            "                 $@$$$@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@$$$$$$$$              ",
            "                 $@@@$$@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@$@@@$                 ",
            "                 $@@@@$@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@$                 ",
            "                 $@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@$                 ",
            "                $$@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@$$                ",
            "                $@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@$                ",
            "                $@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@$                ",
            "               $$@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@$                ",
            "               $@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@$                ",
            "               $@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@$$               ",
            "              $$@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@WWWWWWWWWWWWWW@@@$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$@WWWWWWWWWW@@@@@@@@@@@$               ",
            "              $@@@@@@$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$qWWWWWWWWWWWWWWq$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$qWWWWWWWWWWWWWWq$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@@@WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW@@@@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@@W$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$WW@@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$qqqqqqqqqqqqqqqq$@@W$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$W@@@$qqqqqqqqqqqqqqqq$@@@@@@$               ",
            "              $@@@@@@$$$$$$$$$$$$$$$$$$@@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$@@@$$$$$$$$$$$$$$$$$$@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$               ",
            "             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$              ",
            "             $@@WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW@@@$              ",
            "             $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$              ",
            "             $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$              ",
            "             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$              ",
            "              $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$               ",
            "              $@@@@@$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@@@@@@@$$$$$$$$$$$$$$$@@@@@$               ",
            "              $@@@@@$WWWWWWWWWWWqq$@@@@@@@@@@@@@@@@@@@@@$$WWWWWWWWWWWWWWWWWWWWWW$@@@@@@@@@@@@@@@@@@@@@@@$WWWWWWWWWWWWq$@@@@@$               ",
            "              $@@@@@$qqqqqqqqqqqqq$@@@@@@@@@@@@@@@@@@@@@$WWW$WWW$$$WW$$$$$W$$$$W$$@@@@@@@@@@@@@@@@@@@@@@$qqqqqqqqqqqqq$@@@@@$               ",
            "              $@@@@@$$$$$$$$$$$$$$$@@$$$$$$$$$$$$$$$$$$$$WWW$WWW$WW$WWW$WWW$WWWWW$$$$$$$$$$$$$$$$$$$$$@@$$$$$$$$$$$$$$$@@@@@$               ",
            "              $@@@@@@@@@@@@@@@@@@@@$$WWWWWWWWWWWWWWWWWWW$WWW$WWW$WWW$WW$WWW$WWWWW$WWWWWWWWWWWWWWWWWWWW$$@@@@@@@@@@@@@@@@@@@@$               ",
            "              $$$$$$$$$$$$$$$$$$$$$$WW$$$$$$$$$$$$$$$$$$$WWW$WWW$WWW$WW$WWW$$$$WW$$$$$$$$$$$$$$$$$$$$WW$$$$$$$$$$$$$$$$$$$$$$               ",
            "               $$$$$$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@$WWW$WWW$WWW$WW$WWWWWW$WW$@@@@@@@@@@@@@@@@@@$$$$$$$$$$$$$$$$$$$$$$$$                ",
            "               $$@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@$WWW$WWW$WWW$WW$WWWWWW$WW$@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@@$$                ",
            "                $@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@$WWW$WWW$WW$WWW$WWWWWW$WW$@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@$$                 ",
            "                $@@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@$$WW$$$W$$$WWWW$WWW$$$$WW$@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@@$                  ",
            "                 $@@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@$WWWWWWWWWWWWWWWWWWWWWW$$@@@@@@@@@@@@@@@@@@$$@@@@@@@@@@@@@@@@@@$$                  ",
            "                 $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                  ",
            "                  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                   ",
            "                  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                   ",
            "                  $$$$$$$$$$$$$$$$$$$$                                                               $$$$$$$$$$$$$$$$$$$$                   ",
            "                  $$$$$$$$$$$$$$$$$$$$                                                               $$$$$$$$$$$$$$$$$$$                    ",
            "                  $$$$$$$$$$$$$$$$$$$                                                                $$$$$$$$$$$$$$$$$$$                    ",
            "                  $$$$$$$$$$$$$$$$$$$                                                                $$$$$$$$$$$$$$$$$$$                    ",
            "                   $$$$$$$$$$$$$$$$$                                                                  $$$$$$$$$$$$$$$$$                     ",
            "                    $$$$$$$$$$$$$$$$                                                                   $$$$$$$$$$$$$$$$                     ",
            "                      $$$$$$$$$$$$$                                                                      $$$$$$$$$$$$$                      "
    };

    @BeforeEach
    void init() {
        carMock = Mockito.mock(Car.class);
        positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10);
        Mockito.when(positionMock.getY()).thenReturn(20);
        Mockito.when(carMock.getPosition()).thenReturn(positionMock);
        guiMock = Mockito.mock(GUI.class);
        carView = Mockito.spy(CarView.class);
    }

    @Test
    void draw() {
        carView.draw(carMock, guiMock);
        Mockito.verify(guiMock).drawImage(Mockito.any(Position.class), Mockito.eq(CAR_image));
    }

}
