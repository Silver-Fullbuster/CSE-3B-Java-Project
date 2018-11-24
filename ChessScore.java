/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kartikeyachaauhan
 */

class ChessScore extends Score{
        
	float elo;
        
	public ChessScore(String name, float time, float elo){
            super(name, time);
            this.elo = elo;
	} //Add number of pieces left
        
        @Override
        public String getScore(){
            return name + "\t" + time + "\t" + "Chess ELO Rating: " + elo;
        }
}

