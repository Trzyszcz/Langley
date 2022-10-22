+ Langley {
	*floor_drums {
		HID.findAvailable;

		~myhid  = HID.open( 1699, 1286 );
		~gam = false;
		~gong[1][0].set(\freq, 220);
		~gong[1][1].run(true);
		~gong[1][1].set(\freq, 110);

		HIDdef.device(
			key: \RF,
			func: { |value|
				//"RF ".post;
				//value.postln;
				//if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) });
				//if( (~gam), { if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) }) } );
				if( (~gam),
					{ if( (value==0), {~gong[1][0].set(\gate, 1) }, {~gong[1][0].set(\gate, 0) }) } ,
					{ if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) }) },
				);
			},
			elUsageName: \Y,
			deviceName: "Saitek R220 ",
		);

		HIDdef.device(
			key: \LF,
			func: { |value|

				if( (~gam),
					{ if( (value==0), {~gong[1][1].set(\gate, 1) }, {~gong[1][1].set(\gate, 0) }) },
					{ if( (value==0), {~bassdrum.set(\gate, 1) }, {~bassdrum.set(\gate, 0) }) },
				);
				/*if( (value==0),
					{
						//~hihat.set(\gain, gain);
						~bassdrum.set(\gate, 1);
							//"dum".postln;
					},
					{
						~bassdrum.set(\gate, 0);
					}
				);*/
			},
			elUsageName: \Slider,
			deviceName: "Saitek R220 ",
		);
		Routine {
		SynthDef(
			name: "Snare",
			ugenGraphFunc: {
				arg freq = 220,
				    gate = 0,
				    gain = 1,
					rightpush = 0;
				var result = FaustSnare.ar(rightpush, freq, gain, gate);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Bassdrum",
			ugenGraphFunc: {
				arg //freq = 80,
				    gate = 0,
				    gain = 1,
		            attack = 0.001,
		            decay = 0.15;
		            //freq_decay = 0.1,
		            //freq_slide = 0.0;
				var result = FaustBassdrum.ar(attack, decay, gain, gate);
				Out.ar(0, result);
			}
		).add;

		2.wait;


		~snare = Synth(
				defName: "Snare",
				//target: s,
				addAction: 'addToHead'
			);

		~bassdrum = Synth(
				defName: "Bassdrum",
				//target: s,
				addAction: 'addToHead'
			);
		}.play;
		}
}
