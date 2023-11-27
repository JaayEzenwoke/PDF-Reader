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

package com.jaay.pdfreader.uiviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jaay.pdfreader.R;
import com.jaay.pdfreader.utils.Wallpaper;

public class Frame extends FrameLayout {

    public Frame(@NonNull Context context) {
        super(context);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attributes) {
        super(context, attributes);
        initialize(context, attributes);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attributes, int style) {
        super(context, attributes, style);
        initialize(context, attributes);
    }

    public void initialize(Context context, AttributeSet attributes) {
        Wallpaper wallpaper = new Wallpaper();
        TypedArray stylable = context.obtainStyledAttributes(attributes, R.styleable.Frame);
        try{
            float radius = stylable.getDimensionPixelSize(R.styleable.Frame_radius, 0);
            float left = stylable.getDimensionPixelSize(R.styleable.Frame_left_radius, 0);
            float top = stylable.getDimensionPixelSize(R.styleable.Frame_top_radius, 0);
            float right = stylable.getDimensionPixelSize(R.styleable.Frame_right_radius, 0);
            float bottom = stylable.getDimensionPixelSize(R.styleable.Frame_bottom_radius, 0);
            int shade = stylable.getColor(R.styleable.Frame_shade, 0);
            float[] radii = new float[]{left, top, right, bottom};

            wallpaper.setRadius(radius, radii);
            wallpaper.setShade(shade);
            wallpaper.setWallpaper();
            setBackground(wallpaper.getWalllpaper());
        }finally {
            stylable.recycle();
        }

    }
}