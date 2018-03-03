using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMove : MonoBehaviour {

	//wthhold the character controller component
	CharacterController charControl;

	//determine the walk speed
	public float walkSpeed;

	//determine gravity
	float gravity = -9.8f;

	// note: Awake is used to initialize any var or game state before the game starts.
	// Awake is called only once during the lifetime of the script instance
	void Awake () {
		charControl = GetComponent<CharacterController> ();
	}
	
	// Update is called once per frame
	void Update () {

		//move the player
		MovePlayer ();
	}

	//move the player
	void MovePlayer() {

		//get the horizontal and vertical input * the walk speed
		float horiz = Input.GetAxis("Horizontal") * walkSpeed;
		float vert = Input.GetAxis("Vertical") * walkSpeed;

		//calling move side (horizontal) and forward (vertical)
		Vector3 movement = new Vector3(horiz, 0, vert);

		//limit the maximum speed of the player
		movement = Vector3.ClampMagnitude (movement, walkSpeed);

		// set y = gravity
		movement.y = gravity;

		// ensure the speed the player moves does not change based on frame rate
		movement = movement * Time.deltaTime;

		// transform direction from local space to world space
		movement = transform.TransformDirection (movement);

		//access the controller, able to move left right forward backward
		charControl.Move (movement);
	}
		
}
