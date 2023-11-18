package com.aor.CrossingGuardJoe.view;

import com.aor.CrossingGuardJoe.gui.LanternaGUI;
import com.aor.CrossingGuardJoe.view.drawer.Draw;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class KidViewer extends View{
    private TextGraphics graphics;
    private Draw draw;
    private int x;  //position x
    private int y;  //position y
    private String[] kidStandImage = new String[]{
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                    $$$$$                         ",
        "                 $$$$$$$$$                        ",
        "                $$$$$$$$$$$$                      ",
        "               $$$$$$$$$$$$$$                     ",
        "              $$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$%%$$$$$$$$$$                   ",
        "               $%%%%%$$$$$$$$$$                   ",
        "               $%%%%%$$$$$$$$$$                   ",
        "               $%%%%%%%%$$$$$$$                   ",
        "               $%%%%%%%%$$$$$$                    ",
        "               $%%%%%%%%$$$$$$                    ",
        "               $$%%%%%%%$$$$$                     ",
        "                $%%%%%%%$$$                       ",
        "                $$%%%%%%$$$                       ",
        "                 $$$$%$$$$$$                      ",
        "                    $$$))))$$                     ",
        "                    $$))))))$$                    ",
        "                    $$$$$$$))$                    ",
        "                   $$$'$''$$)$$                   ",
        "                   $$''$'''$))$$$                 ",
        "                  $$''$$'''$$)))$$$               ",
        "                  $'''$'''''$)))))$               ",
        "                  $'''$'''''$))))$$               ",
        "                  $'''$$$$$$$)))))$               ",
        "                  $''''$%%%$$)))))$               ",
        "                  $''''$%%%$$)))))$               ",
        "                  $''''$%%%$$)))))$$              ",
        "                  $''''$%%%$$)))$$)$              ",
        "                  $'''$$%%%$$))))))$$             ",
        "                  $'''$%%%%$$)))))))$             ",
        "                  $'''$%%%%$$)))$$))$             ",
        "                  $'''$%%%$$$)))$$)$$             ",
        "                  $''$$%%%$'$))$$$$$              ",
        "                  $''$%%%$$'$))$                  ",
        "                  $$$%%%$$''$$$$                  ",
        "                  $$%%%$$$$$$                     ",
        "                  $%%%$$(((($                     ",
        "                  $%%%$((((($                     ",
        "                  $$$$$((((($                     ",
        "                  $((((((((($                     ",
        "                  $((((((((($                     ",
        "                  $((((((((($                     ",
        "                  $(((((((((($                    ",
        "                   $((((((((($                    ",
        "                   $((((((((($                    ",
        "                   $((((((((($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                   $(((((($(($                    ",
        "                  $$$$$$$$$$$$$                   ",
        "                 $$&&&&&&&&$&&$$                  ",
        "                $&&&&&&&&&&$&&&$                  ",
        "                $&&&&&&&&&&$&&$$                  ",
        "                $&&&&&&&&&&$&&$                   ",
        "                $$$$$$$$$$$$$$$                   ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  "
    };

    private String[] kidWalkImage = new String[] {
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                    $$$$$                         ",
        "                 $$$$$$$$$                        ",
        "                $$$$$$$$$$$$                      ",
        "               $$$$$$$$$$$$$$                     ",
        "              $$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$$$$$$$$$$$$$                   ",
        "             $$$$$$%%$$$$$$$$$$                   ",
        "               $%%%%%$$$$$$$$$$                   ",
        "               $%%%%%$$$$$$$$$$                   ",
        "               $%%%%%%%%$$$$$$$                   ",
        "               $%%%%%%%%$$$$$$                    ",
        "               $%%%%%%%%$$$$$$                    ",
        "               $$%%%%%%%$$$$$                     ",
        "                $%%%%%%%$$$                       ",
        "                $$%%%%%%$$$                       ",
        "                 $$$$%$$$$$$                      ",
        "                    $$$))))$$                     ",
        "                    $$))))))$$                    ",
        "                    $$$$$$$))$                    ",
        "                   $$$'$''$$)$$                   ",
        "                   $$''$'''$))$$$                 ",
        "                  $$''$$'''$$)))$$$               ",
        "                  $'''$'''''$)))))$               ",
        "                  $'''$'''''$))))$$               ",
        "                  $'''$$$$$$$)))))$               ",
        "                  $''''$%%%$$)))))$               ",
        "                  $''''$%%%$$)))))$               ",
        "                  $''''$%%%$$)))))$$              ",
        "                  $''''$%%%$$)))$$)$              ",
        "                  $'''$$%%%$$))))))$$             ",
        "                  $'''$%%%%$$)))))))$             ",
        "                  $'''$%%%%$$)))$$))$             ",
        "                  $'''$%%%$$$)))$$)$$             ",
        "                  $''$$%%%$'$))$$$$$              ",
        "                  $''$%%%$$'$))$                  ",
        "                  $$$%%%$$''$$$$                  ",
        "                  $$%%%$$$$$$                     ",
        "                  $%%%$$(((($                     ",
        "                  $%%%$((((($                     ",
        "                 $$$$$$((((($$                    ",
        "                 $((((((((((($                    ",
        "                 $((((((((((($$                   ",
        "                $$(((((((((((($                   ",
        "                $(((((((((((((($                  ",
        "               $$((((((($(((((($$                 ",
        "               $((((((($$((((((($                 ",
        "              $$((((((($(((((((($$                ",
        "              $((((((($$$(((((((($                ",
        "             $$(((((($$ $(((((((($$               ",
        "            $$(((((($$   $(((((((($               ",
        "           $$(((((($$     $((((((($$              ",
        "           $((((((($       $((((((($$             ",
        "          $$(((((($$        $$(((((($$            ",
        "          $(((((($$          $$(((((($$           ",
        "         $$(((((($            $$$((((($$          ",
        "         $(((((($               $$((((($$         ",
        "        $$(((((($                $$(((((($$$$     ",
        "       $$(((((($                  $$((($$$&&$     ",
        "     $$$((((((($                   $((($&&&&$     ",
        "     $$$$$$$$$$$                   $$($&&&&&$     ",
        "     $&&&&&&&&&$                    $$$&&&&$$     ",
        "     $&&&&&&&&$$                    $$&&&&&$      ",
        "     $&&&&&&&&$                     $&&&&&$$      ",
        "      $$$$$$$$$                     $$&&&&$       ",
        "                                     $$$$$        ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  ",
        "                                                  "

    };

    private void drawKidStand(int x, int y, TextGraphics graphics) {
        this.draw = new Draw(graphics);
        draw.drawImage(x, y, kidStandImage);
    }

    private void drawKidWalk(int x, int y, TextGraphics graphics) {
        this.draw = new Draw(graphics);
        draw.drawImage(x, y, kidWalkImage);
    }

    @Override
    public void draw() throws IOException {
        drawKidWalk(x, y, graphics);
    }
}
