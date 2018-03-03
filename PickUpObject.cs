using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PickUpObject : MonoBehaviour {

	public Texture2D pickupTextture;
	public CursorMode curMode = CursorMode.Auto;
	public Vector2 hotspot = Vector2.zero;

	// Use this for initialization, hide the cursor
	void Start () {
		Cursor.visible = false;
	}

	//when mouse enters the object
	void OnMouseEnter() {

		//if it's a pickup object, then change the cursor to pickup cursor
		if (gameObject.tag == "pickup") {
			Cursor.visible = true;
			Cursor.SetCursor (pickupTextture, hotspot, curMode);
		} 

		else {

			//otherwise hide the cursor
			Cursor.visible = false;
		}
	}

	// when mouse exit from an object, hide the cursor
	void OnMouseExit() {
		Cursor.visible = false;
	}
	

}
