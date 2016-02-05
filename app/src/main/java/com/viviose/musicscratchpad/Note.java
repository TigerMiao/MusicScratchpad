package com.viviose.musicscratchpad;

import android.util.DisplayMetrics;

import java.util.ArrayList;

/**
 * Created by Patrick on 2/3/2016.
 */
public class Note{
    public float x;
    public float y;
    NoteName name;
    int octave;
    public enum NoteName{
        c,
        cs,
        d,
        ds,
        e,
        f,
        fs,
        g,
        gs,
        a,
        as,
        b
    }
    private NoteName[] altoStandardNotes = {NoteName.f, NoteName.g, NoteName.a, NoteName.b, NoteName.c, NoteName.d, NoteName.e, NoteName.f, NoteName.g};
    public enum Clef{
        ALTO,
        TREBLE,
        BASS
    }

    public class NotePosn{
        NoteName noteName;
        float yLower;
        float yUpper;
        public NotePosn(NoteName n, float yL, float yU){
            noteName = n;
            yLower = yL;
            yUpper = yU;
        }
    }

    private ArrayList ALTO_POSN = new ArrayList();

    private ArrayList<NotePosn> getNotePosn(float y, Clef clef){
        NoteName[] iterArray = new NoteName[9];
        ArrayList<NotePosn> res = new ArrayList();
        switch (clef){
            case ALTO:
                iterArray = altoStandardNotes;
                res = ALTO_POSN;
        }
        for (int i = 8; i > -1; i--){
            float yL = 1250 - 100 * i;
            float yU = 1350 - 100 * i;
            res.add(new NotePosn(iterArray[i], yL, yU));
        }
        return res;
    }

    private NoteName getNoteFromPosn(float y, Clef clef){
        ArrayList<NotePosn> noteList = getNotePosn(y, clef);
        for (int i = 0; i < noteList.size(); i++) {
            if (y > noteList.get(i).yLower && y <= noteList.get(i).yUpper){
                return noteList.get(i).noteName;
            }
        }
        return NoteName.c;
    }


    public Note(float xC, float yC){
        x = xC;
        y = snapNoteY(yC);
        name = getNoteFromPosn(yC, Clef.ALTO);
        octave = 3;

    }



    private float snapNoteY(float y) {
        float snapY;

        if (450 <= y && 550 >= y) {
            snapY = 500;
        }else if (550 < y && 650 >= y){
            snapY = 600;
        }else if (650 < y && 750 >= y){
            snapY = 700;
        }else if (750 < y && 850 >= y){
            snapY = 800;
        }else if (850 < y && 950 >= y){
            snapY = 900;
        }else if (950 < y && 1050 >= y){
            snapY = 1000;
        }else if (1050 < y && 1150 >= y){
            snapY = 1100;
        }else if (1150 < y && 1250 >= y){
            snapY = 1200;
        }else if (1250 < y && 1350 >= y){
            snapY = 1300;
        }else{
            snapY = -1000;
        }

        return snapY;
    }


}
