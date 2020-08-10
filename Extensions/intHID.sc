+ Langley {
	*intHID {
		| message_type, value |
		if ( Langley.typeHIDint == "valve",
			Langley.valveHID(message_type, value),
		)
	}
} 
