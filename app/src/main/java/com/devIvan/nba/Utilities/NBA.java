package com.devIvan.nba.Utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.devIvan.nba.NBA.Equipo;
import com.devIvan.nba.R;

import java.util.ArrayList;

public class NBA {
    public static ArrayList<Equipo> listEquipo;
    public static com.devIvan.nba.NBA.user user;

    public static int findTeamPicture(String team){
        String _team = team.replace(" ","");
        switch (_team.toLowerCase()){
            case "philadelphia76ers": return R.drawable.logo76;
            case "atlantahawks": return  R.drawable.logoatlanta;
            case "chicagobulls": return  R.drawable.logobulls;
            case "bostonceltics": return  R.drawable.logoceltics;
            case "charlottehornets": return  R.drawable.logocharllotte;
            case "clevelandcavaliers": return  R.drawable.logocleveland;
            case "angelesclippers": return  R.drawable.logoclippers;
            case "dallasmavericks": return  R.drawable.logodallas;
            case "denvernuggets": return  R.drawable.logodenver;
            case "detroitpistons": return  R.drawable.logodetroit;
            case "houstonrockets": return  R.drawable.logohouston;
            case "utahjazz": return  R.drawable.logojazz;
            case "sacramentokings": return  R.drawable.logokings;
            case "newyorkknicks": return  R.drawable.logoknicks;
            case "angeleslakers": return  R.drawable.logolakers;
            case "memphisgrizzlies": return  R.drawable.logomemphis;
            case "miamiheat": return  R.drawable.logomiami;
            case "milwaukeebucks": return  R.drawable.logomilwau;
            case "brooklynnets": return  R.drawable.logonets;
            case "orlandomagic": return  R.drawable.logoorlando;
            case "indianapacers": return  R.drawable.logopacers;
            case "neworleanspelicans": return  R.drawable.logopelicans;
            case "portlandtrailblazers": return  R.drawable.logoportland;
            case "torontoraptors": return  R.drawable.logoraptors;
            case "sanantoniospurs": return  R.drawable.logospurs;
            case "phoenixsuns": return  R.drawable.logosuns;
            case "oklahomacitythunder": return  R.drawable.logothunder;
            case "minnesotatimberwolves": return  R.drawable.logotimberwols;
            case "goldenstatewarriors": return  R.drawable.logowarriors;
            case "washingtonwizards": return  R.drawable.logowizards;
            default: return R.drawable.logo_nba;
        }
    }

    public static void setDorsalNumberWithLotties(int number, LottieAnimationView lottie_1, LottieAnimationView lottie_2, LottieAnimationView lottie_3){
        char[] numberArray = String.valueOf(number).toCharArray();
        final LottieAnimationView[] lottieArray = {lottie_3,lottie_2,lottie_1};
        int len = numberArray.length;
        if (len == 1){
            if (numberArray[0] == '0'){
                lottieArray[1].setAnimation("strip.json");
                lottieArray[1].setSpeed(2);
                lottieArray[1].playAnimation();
            } else {
             lottieArray[1].setAnimation(numberArray[0] + "_lottie_colorful" + ".json");
            }
            lottieArray[0].setAnimation("strip.json");
            lottieArray[0].setSpeed(2);
            lottieArray[0].playAnimation();
            //
            lottieArray[2].setAnimation("strip.json");
            lottieArray[2].setSpeed(2);
            lottieArray[2].playAnimation();
            //
            lottieArray[1].playAnimation();
            lottieArray[1].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[1].setProgress(59);
                    lottieArray[1].pauseAnimation();
                }
            });
        } else if (len == 2){
            lottieArray[0].setAnimation(numberArray[1] + "_lottie_colorful" + ".json");
            lottieArray[2].setAnimation("strip.json");
            lottieArray[2].setSpeed(2);
            lottieArray[2].playAnimation();
            //
            lottieArray[0].playAnimation();
            lottieArray[0].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[0].setProgress(59);
                    lottieArray[0].pauseAnimation();
                }
            });
            lottieArray[1].setAnimation(numberArray[0] + "_lottie_colorful" + ".json");
            lottieArray[1].playAnimation();
            lottieArray[1].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[1].setProgress(59);
                    lottieArray[1].pauseAnimation();
                }
            });
        } else if (len == 3){
            lottieArray[0].setAnimation(numberArray[2] + "_lottie_colorful" + ".json");
            lottieArray[0].playAnimation();
            lottieArray[0].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[0].setProgress(59);
                    lottieArray[0].pauseAnimation();
                }
            });
            lottieArray[1].setAnimation(numberArray[1] + "_lottie_colorful" + ".json");
            lottieArray[1].playAnimation();
            lottieArray[1].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[1].setProgress(59);
                    lottieArray[1].pauseAnimation();
                }
            });
            lottieArray[2].setAnimation(numberArray[0] + "_lottie_colorful" + ".json");
            lottieArray[2].playAnimation();
            lottieArray[2].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[2].setProgress(59);
                    lottieArray[2].pauseAnimation();
                }
            });
        } else if (len > 3){
            lottieArray[0].setAnimation('9' + "_lottie_colorful" + ".json");
            lottieArray[0].playAnimation();
            lottieArray[0].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[0].setProgress(59);
                    lottieArray[0].pauseAnimation();
                }
            });
            lottieArray[1].setAnimation('9' + "_lottie_colorful" + ".json");
            lottieArray[1].playAnimation();
            lottieArray[1].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[1].setProgress(59);
                    lottieArray[1].pauseAnimation();
                }
            });
            lottieArray[2].setAnimation('9' + "_lottie_colorful" + ".json");
            lottieArray[2] .playAnimation();
            lottieArray[2].addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lottieArray[2].setProgress(59);
                    lottieArray[2].pauseAnimation();
                }
            });
        }
    }
}
