package de.ndn.game.platformer;

import com.badlogic.gdx.Game;

import de.ndn.game.platformer.screens.PlayScreen;

public class PlatformerGame extends Game {
	
	@Override
	public void create () {
		setScreen(new PlayScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
