+ Langley {
	*makesynths {

		Routine {
		SynthDef(
			name: "Normie",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustNormik3.ar(freq * pitchbend, gain, gate, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Gutter",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustGutter.ar(freq * pitchbend, gain, gate, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Mou",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustMou.ar(freq * pitchbend, gain, gate, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Hihat",
			ugenGraphFunc: {
				arg freq = 440,
				    gate = 0,
				    gain = 0.3;
				var result = FaustHihat.ar(freq, gain, gate);
				Out.ar(0, result);
			}
		).add;

		2.wait;

		~normie = Synth(
			defName: "Normie",
			//target: s,
			addAction: 'addToHead'
			);

		~gutter = Synth(
			defName: "Gutter",
			//target: s,
			addAction: 'addToHead'
			);

		~hihat = Synth(
				defName: "Hihat",
				//target: s,
				addAction: 'addToHead'
			);

		~mou = Synth(
				defName: "Mou",
				//target: s,
				addAction: 'addToHead'
			);

		Langley.setcurrentSynth ( ~normie );
		}.play;
		}
}
