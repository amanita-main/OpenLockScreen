package com.android.utils;

import android.content.Context;
import android.content.res.Resources;

public class ResourcesHelper {
	
	public static int getResourceId(Context ctx, String defType, String name) {
		Resources res = ctx.getResources();
		return res.getIdentifier(name, defType, ctx.getPackageName());		
	}
	
}
