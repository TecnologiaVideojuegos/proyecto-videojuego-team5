/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoSeleccion extends BasicGameState {

    private Sprite ALFREDO;
    private Sprite MOLDOVA;
    private Sprite LUDWIG;
    private Image fondo, narrador,hudAlfredo, hudMoldova, hudMozart, backAlfredo, backMoldova, backMozart, dialAlfredo, dialMoldova, dialLudwig, flechaDer, flechaIzq;
    private int indicador, contadorIntro,dato;
    private Personaje AlfredoMercurio, LudwigvanMozart, MoldovaSax;
    private SpriteSheet spriteAlfredoD, spriteAlfredoI, spriteLudwigD, spriteLudwigI, spriteMoldovaD, spriteMoldovaI, spriteAlfredoC, spriteLudwigC, spriteMoldovaC, spriteAlfredoB, spriteMoldovaB, spriteLudwigB;
    private Animation alfredoD, alfredoI, ludwigD, ludwigI, moldovaD, moldovaI, alfredoC, ludwigC, moldovaC, alfredoB, moldovaB, ludwigB;
    private Sound fail, potion;
    private boolean introduccion;
    private String texto;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ClaseEstatica.setFx_potion(new Sound("Musica/Sonidos/fx_pot.ogg"));
        this.fondo = new Image("Design/escenario.jpg");
        ALFREDO = new Sprite("Design/FreddieStillBIG.png", 500, 380);
        MOLDOVA = new Sprite("Design/SaxGuyStillBIG.png", 500, 380);
        LUDWIG = new Sprite("Design/BombinStillBIG.png", 500, 380);
        this.indicador = 0;
        hudAlfredo = new Image("Design/battlev1UI.png");
        hudMoldova = new Image("Design/battlev2UI.png");
        hudMozart = new Image("Design/battlev3UI.png");
        backMozart = new Image("Design/battlebombin1.png");
        backAlfredo = new Image("Design/battleFreddie1.png");
        backMoldova = new Image("Design/battleSax1.png");
        dialAlfredo = new Image("Design/dialogoFreddie1.png");
        dialLudwig = new Image("Design/dialogoBombin1.png");
        dialMoldova = new Image("Design/dialogoSax1.png");
        fail = new Sound("Musica/Sonidos/fx_fail.ogg");

        flechaDer = new Image("Design/FlechaDerecha.png");
        flechaIzq = new Image("Design/FlechaIzquierda.png");
        narrador = new Image("Design/dialogoNarrador1.png");

        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();

        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67, 164);
        spriteAlfredoC = new SpriteSheet("Design/battleFreddieSprite.png", 181, 345);
        spriteAlfredoB = new SpriteSheet("Design/FreddieDance1.png", 105, 171);
        alfredoD = new Animation(spriteAlfredoD, 100);
        alfredoI = new Animation(spriteAlfredoI, 100);
        alfredoC = new Animation(spriteAlfredoC, 100);
        alfredoB = new Animation(spriteAlfredoB, 100);
        Music musicAlfredoB = new Music("Musica/rock_8bit.ogg", false);
        Music musicAlfredoH = new Music("Musica/rock_8hit.ogg", false);
        Music musicAlfredoBN = new Music("Musica/rock_battle.ogg", false);
        Music musicAlfredoHN = new Music("Musica/rock_hall.ogg", false);

        spriteLudwigC = new SpriteSheet("Design/battleBombinSprite.png", 198, 345);
        spriteLudwigD = new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167);
        spriteLudwigI = new SpriteSheet("Design/BombinWalkSprite_V3.png", 71, 167);
        spriteLudwigB = new SpriteSheet("Design/BombinDance1.png", 107, 171);
        ludwigC = new Animation(spriteLudwigC, 100);
        ludwigD = new Animation(spriteLudwigD, 100);
        ludwigI = new Animation(spriteLudwigI, 100);
        ludwigB = new Animation(spriteLudwigB, 100);
        Music musicLudwigB = new Music("Musica/classic_8bit.ogg", false);
        Music musicLudwigH = new Music("Musica/classic_8hit.ogg", false);
        Music musicLudwigBN = new Music("Musica/classic_battle.ogg", false);
        Music musicLudwigHN = new Music("Musica/classic_hall.ogg", false);

        spriteMoldovaC = new SpriteSheet("Design/battleSaxSprite.png", 174, 345);
        spriteMoldovaD = new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 67, 176);
        spriteMoldovaI = new SpriteSheet("Design/SaxGuyWalkSprite_V3.png", 67, 176);
        spriteMoldovaB = new SpriteSheet("Design/SaxGuyDance1.png", 106, 171);
        moldovaC = new Animation(spriteMoldovaC, 100);
        moldovaD = new Animation(spriteMoldovaD, 100);
        moldovaI = new Animation(spriteMoldovaI, 100);
        moldovaB = new Animation(spriteMoldovaB, 100);
        Music musicMoldovaB = new Music("Musica/jazz_8bit.ogg", false);
        Music musicMoldovaH = new Music("Musica/jazz_8hit.ogg", false);
        Music musicMoldovaBN = new Music("Musica/jazz_battle.ogg", false);
        Music musicMoldovaHN = new Music("Musica/jazz_hall.ogg", false);

        Ataque Guitarrazo = new Ataque(50, 30, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 20, new Sound("Musica/Sonidos/fx_arock1.ogg"));
        Ataque Mama = new Ataque(70, 10, "Mama", "Inflingirá un daño brutal en los tímpanos del enemigo", 30, new Sound("Musica/Sonidos/fx_arock2.ogg"));
        Ataque DiscoPlatino = new Ataque(100, 5, "Disco de platino", "Lanzará uno de sus discos de platino a la yugular para causar un daño LETAL!!!", 50, new Sound("Musica/Sonidos/fx_arock3.ogg"));

        Ataque Saxofonazo = new Ataque(40, 30, "Saxofonazo", "Lanzará un saxofón para causar un daño leve", 20, new Sound("Musica/Sonidos/fx_ajazz1.ogg"));
        Ataque BaileSwing = new Ataque(60, 10, "Al ritmo del Swing", "Te romperá las caderas con solo mirarle bailar!!", 30, new Sound("Musica/Sonidos/fx_ajazz2.ogg"));
        Ataque SaxGuy = new Ataque(90, 5, "SaxGuy", "Usará la mítica canción de Eurovisión para causar un daño LETAL!!", 40, new Sound("Musica/Sonidos/fx_ajazz3.ogg"));

        Ataque Pianazo = new Ataque(35, 30, "Pianazo", "Lanzará un piano para causar un daño leve", 10, new Sound("Musica/Sonidos/fx_aclassic1.ogg"));
        Ataque MetricaExacta = new Ataque(55, 10, "Metrica Exacta", "Regañará al enemigo por no llevar el ritmo acorde e inflingirá daño por humillación", 20, new Sound("Musica/Sonidos/fx_aclassic2.ogg"));
        Ataque PelucoVictoriano = new Ataque(75, 5, "Peluco Victoriano", "Lanzará su tremenda peluca para destrozar los sueños capilares del enemigo, causando un daño LETAL!!!", 40, new Sound("Musica/Sonidos/fx_aclassic3.ogg"));

        LudwigvanMozart = new Personaje(1000, "Ludwin van Mozart", new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167), ludwigD, ludwigI, musicLudwigB, musicLudwigH, musicLudwigBN, musicLudwigHN, hudMozart, ludwigC, ludwigB, dialLudwig, 0, 0, fail, 0, 0);
        LudwigvanMozart.getAtaques().add(Pianazo);
        LudwigvanMozart.getAtaques().add(MetricaExacta);
        LudwigvanMozart.getAtaques().add(PelucoVictoriano);

        AlfredoMercurio = new Personaje(700, "Alfredo Mercurio", new SpriteSheet("Design/FreddieStillBIG.png", 69, 164), alfredoD, alfredoI, musicAlfredoB, musicAlfredoH, musicAlfredoBN, musicAlfredoHN, hudAlfredo, alfredoC, alfredoB, dialAlfredo, 0, 0, fail, 0, 0);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        AlfredoMercurio.getAtaques().add(Mama);
        AlfredoMercurio.getAtaques().add(DiscoPlatino);

        MoldovaSax = new Personaje(900, "Moldova Sax", new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 70, 176), moldovaD, moldovaI, musicMoldovaB, musicMoldovaH, musicMoldovaBN, musicMoldovaHN, hudMoldova, moldovaC, moldovaB, dialMoldova, 0, 0, fail, 0, 0);
        MoldovaSax.getAtaques().add(Saxofonazo);
        MoldovaSax.getAtaques().add(BaileSwing);
        MoldovaSax.getAtaques().add(SaxGuy);
        ClaseEstatica.setAtaqueAcertado(true);
        ClaseEstatica.setMusicSilence(new Music("Musica/silence.ogg", false));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (!ClaseEstatica.getMusicaMenu().playing()) {
            ClaseEstatica.getMusicaMenu().play();
        }
        if (introduccion) {
            narrador.draw();
            font.drawString(270, 570, texto);
        }
        switch (indicador) {
            case 0:
                ALFREDO.draw();
                flechaDer.draw(630, 425);
                break;
            case 1:
                MOLDOVA.draw();
                flechaDer.draw(630, 425);
                flechaIzq.draw(330, 425);
                break;
            case 2:
                LUDWIG.draw();
                flechaIzq.draw(330, 425);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        dato += delta;
        if (introduccion) {
            if (contadorIntro == 0) {
                texto = "¡Bienvenidos a la batalla por salvar el futuro de la musica!\n\n¿Cual será el héroe que protagonizará para esta trepidante aventura?";
                dato  = 0;
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                texto = "Selecciona un personaje con las flechas del teclado. \n\nSelecciona un personaje pulsando el boton Enter";
                dato  = 0;
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && contadorIntro==2 && (dato > 1000)) {
                introduccion = false;
                dato  = 0;
            }
        } else {
            if (entrada.isKeyPressed(Input.KEY_RIGHT)) {
                if (indicador == 0) {
                    indicador = 1;
                    if (!ClaseEstatica.getClick().playing()) {
                        ClaseEstatica.getClick().play();
                    }
                } else if (indicador == 1) {
                    indicador = 2;
                    if (!ClaseEstatica.getClick().playing()) {
                        ClaseEstatica.getClick().play();
                    }
                }
            } else if (entrada.isKeyPressed(Input.KEY_LEFT)) {
                if (indicador == 1) {
                    if (!ClaseEstatica.getClick().playing()) {
                        ClaseEstatica.getClick().play();
                    }
                    indicador = 0;
                } else if (indicador == 2) {
                    if (!ClaseEstatica.getClick().playing()) {
                        ClaseEstatica.getClick().play();
                    }
                    indicador = 1;
                }

            } else if (entrada.isKeyPressed(Input.KEY_ESCAPE)) {
                game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            } else if (entrada.isKeyPressed(Input.KEY_ENTER) && (dato > 1000)) {
                switch (indicador) {
                    case 0:
                        ClaseEstatica.setPersonaje(AlfredoMercurio);
                        game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                        break;
                    case 1:
                        ClaseEstatica.setPersonaje(MoldovaSax);
                        game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                        break;
                    case 2:
                        ClaseEstatica.setPersonaje(LudwigvanMozart);
                        game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        indicador = 0;
        introduccion = true;
        contadorIntro = 0;
        texto = "";
    }
}
