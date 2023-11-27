/*
 * Copyright 2023 Justice Ezenwoke Chukwuemeka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaay.pdfreader.utils;

import android.graphics.drawable.GradientDrawable;

public class Wallpaper {

    private GradientDrawable walllpaper;
    private float radius;
    private float[] radii;
    private int shade;

    public Wallpaper(){
        walllpaper = new GradientDrawable();
    }

    public GradientDrawable getWalllpaper() {
        return walllpaper;
    }

    public void setWallpaper() {
        if (walllpaper != null) {
            if(radius != 0){
                walllpaper.setCornerRadius(radius);
            }else {
                setRadii(radii[0], radii[1], radii[2], radii[3]);
            }
            walllpaper.setColor(shade);
            walllpaper.setCornerRadius(radius);
        }
    }

    public void setShade(int shade) {
        this.shade = shade;
    }

    public void setRadius(float radius, float[] radii) {
        this.radius = radius;
        this.radii = radii;
    }

    public void setRadii(float left, float top, float right, float bottom) {
        float[] radii = {
                left, left,
                top, top,
                right, right,
                bottom, bottom
        };
        walllpaper.setCornerRadii(radii);
    }
}
