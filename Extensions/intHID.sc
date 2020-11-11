+ Langley {
	*intHID {
		| message_type, value |
		if ( Langley.typeHIDint == "vintage_valve",
			{ Langley.vintage_valveHID(message_type, value) },
		);

		if ( Langley.typeHIDint == "floor_trigger_valve",
			{ Langley.floor_trigger_valveHID(message_type, value) },
		);
	}
}
