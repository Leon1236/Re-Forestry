package com.leon1236.reforestry.core.book.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public final class BookTextHelper {
	private BookTextHelper() {
	}

	public static Component resolve(String text) {
		if (text.isEmpty()) {
			return Component.empty();
		}
		if (text.indexOf('.') >= 0) {
			return format(text);
		}
		return Component.literal(text);
	}

	public static Component format(String translationKey) {
		String raw = Component.translatable(translationKey).getString();
		return parseInline(raw);
	}

	private static Component parseInline(String raw) {
		MutableComponent result = Component.empty();
		Style style = Style.EMPTY;
		StringBuilder buffer = new StringBuilder();
		int index = 0;
		while (index < raw.length()) {
			if (raw.startsWith("$()", index)) {
				flush(result, buffer, style);
				index += 3;
				continue;
			}
			if (raw.startsWith("$(br)", index)) {
				flush(result, buffer, style);
				result.append("\n");
				index += 5;
				continue;
			}
			if (raw.startsWith("$(italic)", index)) {
				flush(result, buffer, style);
				style = style.withItalic(true);
				index += 9;
				continue;
			}
			if (raw.startsWith("$(bold)", index)) {
				flush(result, buffer, style);
				style = style.withBold(true);
				index += 7;
				continue;
			}
			if (raw.startsWith("$(dark_green)", index)) {
				flush(result, buffer, style);
				style = style.withColor(ChatFormatting.DARK_GREEN);
				index += 13;
				continue;
			}
			if (raw.startsWith("$(l)", index) || raw.startsWith("$(9)", index)) {
				flush(result, buffer, style);
				style = style.withColor(ChatFormatting.BLUE);
				index += 4;
				continue;
			}
			buffer.append(raw.charAt(index));
			index++;
		}
		flush(result, buffer, style);
		return result;
	}

	private static void flush(MutableComponent result, StringBuilder buffer, Style style) {
		if (!buffer.isEmpty()) {
			result.append(Component.literal(buffer.toString()).withStyle(style));
			buffer.setLength(0);
		}
	}
}
