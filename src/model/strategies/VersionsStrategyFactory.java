package model.strategies;

public class VersionsStrategyFactory {


	public VersionsStrategy createStrategy(String choice){
		if(choice.equals("Stable")){
			return ( new StableVersionsStrategy());
		
		}else{
			return ( new VolatileVersionsStrategy());
			
		}
	}
}