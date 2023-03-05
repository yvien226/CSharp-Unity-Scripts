/*
 * RobotControl.java
 *
 * This is the control mechanism for the Robot arm - this is the part
 * of the program that you are required to implement.
 * 
 * There are three separate methods which correspond to the the
 * three scenarios your control mechanism needs to be able to handle -
 * if you are going to start with scenario A and then move onto
 * scenarios B and C later then you can finish the control mechanism
 * for scenario first and then implement your control mechanism for
 * scenarios B and C in the corresponding empty control methods.
 *
 * You do not need to do anything for the Robot itself as it has
 * already been fully implemented - you just need to supply the control
 * mechanism(s) to make it move blocks from the source pile to their
 * corresponding target piles.
 */

import javax.swing.*;

class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
	}

	public void control(int[] barHeights, int[] blockHeights) {
		/*
		 * This is the starting point for handling scenario A
		 */

		 controlMechanismForScenarioA(barHeights, blockHeights);

		/*
		 * comment out the method call above and uncomment the method call below
		 * when you're ready to start working on scenario B
		 */

		 //controlMechanismForScenarioB(barHeights, blockHeights);

		/*
		 * comment out the method call above and uncomment the method call below
		 * when you're ready to start working on scenario C
		 */

		//controlMechanismForScenarioC(barHeights, blockHeights);
	}

	/*
	 * If you are working through this assignment one scenario at a time then
	 * you should start implementing the control mechanism for configuration
	 * scenario A here.
	 */
	public void controlMechanismForScenarioA(int barHeights[],
			int blockHeights[]) {

		// slows down the robot a little - adjust to suit your
		// environment/preference
		r.slowDown(2);

		// Internally the robot arm records the size of each of the
		// three arms - it is recommended that you keep track of the
		// size of these arms as well when implementing yoiur control
		// mechanism

		// Represents the height of arm 1 (starts off at 2)
		int h = 2;

		// Represents the width of arm 2 (starts off at 1)
		int w = 1;

		// Represents the depth of arm 3 (starts off at 0)
		// note that you can't see this arm yet as the third arm has
		// not been lowered.
		//
		// Also note that the3 picker sits at the the end of this third arm.
		int d = 0;

		// We need to move the robot arm up into the starting position -
		// this requires us to move the first arm up and extend the second
		// arm so that it is in line with the source pile

		while (h < 10) {
			r.up();
			h++;
		}

		while (w < 2) {
			r.extend();
			w++;
		}
		// comment this segment of code out when you are ready to start

		// JOptionPane.showMessageDialog(null,
		// "This startup code has picked up one block from source, "
		// + " now move it to the \"highlighted\" bar position.\n"
		// + " When the block has been moved to its destination "
		// + " you need to repeat the same process to move the "
		// + " other three blocks. \n"
		// + "You may modify this code or "
		// + "redesign the program and come up with "
		// + "your own method of controlling the robot.",
		// "Helper Code Execution", JOptionPane.INFORMATION_MESSAGE);

		// now that the arm is in the "starting position" the movement
		// of the blocks can begin - we start by lowering the picker
		// down to the source pile

		// Now you need to do the following:
		//
		// A. move the block over to the target bar (column 8)
		// B. lower the block down into place on top of the target bar
		// C. drop the block off on the target bar
		// D. Return the robot arm back to the "starting position" above
		// the source pile
		//

		// ...
		// ...

		// Once you have moved the first block and returned the robot arm
		// back to the starting position you need to repeat the process for
		// the other three blocks (note that the distances you need to move
		// the arms of the robot may change with each new block)

		// ...
		// ...

		// You have moved one block from source to the first bar position. You
		// should be able to get started now.

		for (int i = 0; i < 4; i++) {

			while (d < 1 + (2 * i)) {
				r.lower();
				d++;
			}
			// then we pick up the block at the top of the source pile
			r.pick();

			// now we raise the picker back up (which lifts the block
			// up off the source pile)
			while (d > 0) {
				r.raise();
				d--;
			}

			while (w < 8 - i) {
				r.extend();
				w++;
			}

			while (d < 3) {
				r.lower();
				d++;
			}
			r.drop();

			while (d > 0) {
				r.raise();
				d--;
			}

			while (w > 2) {
				r.contract();
				w--;
			}

		}

	}

	/*
	 * If you are working through this assignment one scenario at a time then
	 * you should start implementing the control mechanism for configuration
	 * scenario B here.
	 */
	public void controlMechanismForScenarioB(int barHeights[],
			int blockHeights[]) {

		// You may find using these variables will help you, but you can delete
		// them if you want to create/use your own variables whilst implementing
		// the control mechanism in your own way

		int sourceHeight = 0;
		int sourceLocation = 2;

		int h = 2;
		int w = 1;
		int d = 0;
		int j = 0;


		int clearanceHeight = 0;

		for (int i = blockHeights.length-1; i >= 0; i--) {
			sourceHeight += blockHeights[i];
		}
		
		 for (int i = blockHeights.length-1 ; i >= 0 ; i--) {

			while (h < sourceHeight + sourceLocation) {
				r.up();
				h++;
			}

			
			while (w < 2) {
				r.extend();
				w++;
			}

			while (d < h - sourceHeight - 1) {
				r.lower();
				d++;
			}
			r.pick();
			sourceHeight -= blockHeights[i];

			while (d > 0) {
				r.raise();
				d--;
			}

			while (w < 8 - j) {
				r.extend();
				w++;
			}
			

			while (d < h - barHeights[5 - j] - blockHeights[i] - 1) {
				r.lower();
				d++;
			}
			r.drop();
			j++;
			
			if(sourceHeight==0) {break;}

			while (d > clearanceHeight) {
				r.raise();
				d--;
			}

			while (w > 2) {
				r.contract();
				w--;
			}
		  
		}
	}

	/*
	 * If you are working through each stage one at a time -OR- you are going to
	 * implement a mechanism to handle all three configuration scenarios right
	 * from the start then this is where you should begin implementing your
	 * control mechanism for scenario C
	 */
	public void controlMechanismForScenarioC(int barHeights[],
			int blockHeights[]) {

		int sourceHeight = 0;
		int sourceLocation = 2;

		int h = 2;
		int w = 1;
		int d = 0;
		int j = 1;

		int targetBar = barHeights[0];

		int targetHeight = 0 ;
		int clearanceHeight = 0;

		for (int k = 0; k < barHeights.length-1; k++ ){
			if (barHeights[k] > targetBar)
			{
				targetBar = barHeights[k];
			}
		}

		for (int i = blockHeights.length - 1; i >= 0; i--) {
			sourceHeight += blockHeights[i];
		}
	
		for (int i = blockHeights.length - 1; i >= 0; i--) {
    			    	
			while (h < sourceHeight + sourceLocation) {
				r.up();
				h++;
			}


			while (w < 2) {
				r.extend();
				w++;
			}
			
			while (d < h - sourceHeight - 1) {
				r.lower();
				d++;
			}
			r.pick();

			
			sourceHeight -= blockHeights[i];

			while (d > 0) {
				r.raise();
				d--;
			}

			if (blockHeights[i] == 1) {
				while (w < 10) {
					r.extend();
					w++;
				}
				while (d < h- blockHeights[i]-clearanceHeight - 1) {
					r.lower();
					d ++;
				}
				r.drop();
				clearanceHeight++;
				
				if (sourceHeight == 0) {break;}

			} else if (blockHeights[i] == 3) {
				while (w < 9) {
					r.extend();
					w++;
				}
				while (d < h - blockHeights[i] - targetHeight - 1) {
					r.lower();
					d++;
				}
				r.drop();
				targetHeight = targetHeight + 3;

				if (sourceHeight == 0) {break;}

			} else {
					while (w < 9 - j) {
						r.extend();
						w++;
					}

					while (d < h - barHeights[6 - j] - blockHeights[i] - 1) {
						r.lower();
						d++;
					}	
						r.drop();
						j++;
				
					}
			
			    	if (sourceHeight == 0) {break;}
			    
			    	while (d > clearanceHeight) {
			    		r.raise();
			    		d--;
			    	}
			    
			    	while (w > 2) {
			    		r.contract();
			    		w--;
			    	}		
			    	if (targetBar > sourceHeight) {
			    		while (h < targetBar + blockHeights[i] + sourceLocation) {
			    			r.up();
			    			h++;
			    		}
			    	} else {
				    	while (h > sourceHeight + clearanceHeight +blockHeights[i]+ sourceLocation) {
				    		r.down();
				    		h--;
				    	}
			    	}

			}
		}
	
}