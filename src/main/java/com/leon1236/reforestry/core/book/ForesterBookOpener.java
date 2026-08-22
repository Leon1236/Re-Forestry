package com.leon1236.reforestry.core.book;

public final class ForesterBookOpener {
	private static Runnable opener = () -> {
	};

	private ForesterBookOpener() {
	}

	public static void setOpener(Runnable opener) {
		ForesterBookOpener.opener = opener;
	}

	public static void open() {
		opener.run();
	}
}
