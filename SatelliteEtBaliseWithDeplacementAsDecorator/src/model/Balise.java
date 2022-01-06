package model;

import events.SatelitteMoveListener;
import events.SatelliteMoved;

public class Balise extends ElementMobile implements SatelitteMoveListener{
	
	private boolean estSynchronise;
	
	public Balise(int memorySize) {
		super(memorySize);
		this.estSynchronise = false;
	}
	
	public int profondeur() { 
		return this.getPosition().y; 
	}
	
	protected void readSensors() {
		this.dataSize++;
	}
	
	public void tick() {
		if (!this.estSynchronise)
			this.readSensors();
		if (this.memoryFull()) {
			Deplacement redescendre = new Redescendre(this.deplacement(), this.profondeur());
			Deplacement deplSynchro = new DeplSynchronisation(redescendre);
			Deplacement nextDepl = new MonteSurfacePourSynchro(deplSynchro);
			this.setDeplacement(nextDepl);
			this.resetData();
		} 
		this.estSynchronise = false;
		super.tick();
	}

	@Override
	public void whenSatelitteMoved(SatelliteMoved arg) {
		DeplacementBalise dp = (DeplacementBalise) this.depl;
		dp.whenSatelitteMoved(arg, this);
		this.estSynchronise = true;
	}


}
