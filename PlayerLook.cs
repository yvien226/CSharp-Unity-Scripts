using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerLook : MonoBehaviour {

	//control the speed when we move the mouse and rotate
	public float mouseSensitivity;

	float xAxisClamp = 0.0f;

	//the capsule player body
	public Transform playerBody;


	//lock the cursor (make it invisible in the play mode)
	void Awake() {
		//Cursor.lockState = CursorLockMode.Locked;
	}
	
	// Update is called once per frame
	void Update () {
		RotateCamera ();
	}

	void RotateCamera () {

		//retrieve the amount of movement of the mouse on x & y axis
		float mouseX = Input.GetAxis("Mouse X");
		float mouseY = Input.GetAxis("Mouse Y");

		//store the amount where the camera should rotate
		float rotAmountX = mouseX * mouseSensitivity;
		float rotAmountY = mouseY * mouseSensitivity;

		// subtract the amount of rotation at y axis onto xaxisclamp
		xAxisClamp = xAxisClamp - rotAmountY;

		//extract the current rotation of the camera and player body(capsule)
		Vector3 targetRotCam = transform.rotation.eulerAngles;
		Vector3 targetRotBody = playerBody.rotation.eulerAngles;

		//add the amount onto the current rotation (add the amount needs to rotate)
		//*when you move your mouse left right, you're rotating the y axis 
		//*instead of camera rotating up and down, we let its body to rotate up and down on x axis
		targetRotCam.x = targetRotCam.x - rotAmountY;
		targetRotBody.y = targetRotBody.y + rotAmountX;

		//always set z axis to 0 so that the camera doesn't flip
		targetRotCam.z = 0;

		//if xaxisclamp is > 90 deg (camera looking downward), then make the camera 90 deg
		if (xAxisClamp > 90) {
			xAxisClamp = 90;
			targetRotCam.x = 90;
		} 
		//if it's < -90 (camera looking upward), then make the camera 270 deg (max of facing upward)
		else if (xAxisClamp < -90) {
			xAxisClamp = -90;
			targetRotCam.x = 270; 
		}

		//putting the amount back
		transform.rotation = Quaternion.Euler(targetRotCam);
		playerBody.rotation = Quaternion.Euler(targetRotBody);



	}

}
