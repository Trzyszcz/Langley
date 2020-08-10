+ Langley {
	*valveHID { | message_type, value |
		message_type.postln;
		value.postln;
		
		if( message_type == "R1",
			{ Langley.pushvalve(1, value) },
		);
		}
}

