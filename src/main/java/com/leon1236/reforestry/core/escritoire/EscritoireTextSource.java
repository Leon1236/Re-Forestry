package com.leon1236.reforestry.core.escritoire;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

public class EscritoireTextSource {
	private enum Notes {
		level1,
		level2,
		level3,
		level4,
		success,
		failure,
		empty
	}

	private static final ListMultimap<Notes, Component> RESEARCH_NOTES = ArrayListMultimap.create();
	private static final Random RANDOM = new Random();

	static {
		EnumSet<Notes> multiple = EnumSet.of(Notes.level1, Notes.level2, Notes.level3, Notes.level4, Notes.success, Notes.failure);
		for (Notes notesLevel : multiple) {
			for (int i = 1; i <= 10; i++) {
				String key = "for.gui.escritoire.notes." + notesLevel + '.' + i;
				if (Language.getInstance().has(key)) {
					RESEARCH_NOTES.put(notesLevel, Component.translatable(key));
				}
			}
		}
		RESEARCH_NOTES.put(Notes.empty, Component.translatable("for.gui.escritoire.instructions"));
	}

	@Nullable
	private Component researchNote;
	@Nullable
	private Notes lastNoteLevel;

	public Component getText(EscritoireGame escritoireGame) {
		Notes noteLevel = getNoteLevel(escritoireGame);
		if (this.lastNoteLevel != noteLevel || this.researchNote == null) {
			this.researchNote = getRandomNote(noteLevel);
			this.lastNoteLevel = noteLevel;
		}
		return this.researchNote;
	}

	private static Component getRandomNote(Notes level) {
		List<Component> candidates = RESEARCH_NOTES.get(level);
		if (candidates.isEmpty()) {
			return Component.empty();
		}
		return candidates.get(RANDOM.nextInt(candidates.size()));
	}

	private static Notes getNoteLevel(EscritoireGame game) {
		return switch (game.getStatus()) {
			case PLAYING -> {
				int bounty = game.getBountyLevel();
				if (bounty >= EscritoireGame.BOUNTY_MAX) {
					yield Notes.level1;
				} else if (bounty > EscritoireGame.BOUNTY_MAX / 2) {
					yield Notes.level2;
				} else if (bounty > EscritoireGame.BOUNTY_MAX / 4) {
					yield Notes.level3;
				} else {
					yield Notes.level4;
				}
			}
			case FAILURE -> Notes.failure;
			case SUCCESS -> Notes.success;
			case EMPTY -> Notes.empty;
		};
	}
}
