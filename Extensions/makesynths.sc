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
			name: "Organ",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    leftVertical = 0.5,
				    rightVertical = 0.5,
					rightPush = 0,
					leftPush = 0;
				var result = FaustOrgan.ar(leftPush, rightPush, freq * pitchbend, gain, gate, leftVertical, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Vang",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    leftVertical = 0.5,
				    rightVertical = 0.5,
					rightPush = 0,
					leftPush = 0;
				var result = FaustVang.ar(leftPush, rightPush, freq * pitchbend, gain, gate, leftVertical, rightVertical);
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
				    leftVertical = 0.5,
				    rightVertical = 0.5,
					filres = 1;
				var result = FaustGutter.ar(filres, freq * pitchbend, gain, gate, leftVertical, rightVertical);
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
			name: "Uan",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustUan.ar(freq * pitchbend, gain, gate, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;


		SynthDef(
			name: "TestMou",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustTestMou.ar(freq * pitchbend, gain, gate, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "TestMou2",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
					initgain = 1,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustTestMou2.ar(freq * pitchbend, gain, gate, initgain, rightHorizontal, rightVertical);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "TestFMGrain",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
					initgain = 1,
				    leftVertical = 0.5,
				    rightVertical = 0.5,
					ratio = 200/440;
				var result = gate*gain*FMGrain.ar(Impulse.ar(20*leftVertical), 0.14 * rightVertical, freq * pitchbend, ratio * freq * pitchbend,
						//LFNoise1.ar(1).range(1, 10),
						10,
						EnvGen.kr(
							Env([0, 1, 0], [1, 1], \sin, 1),
							1,
							levelScale: 1,
							doneAction: 2)
					);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Gong",
			ugenGraphFunc: {
				arg freq = 13.75,
				    pitchbend = 1,
				    gate = 0,
				    gain = 0.5,
					initgain = 1,
				    rightVertical = 0.5,
				    rightHorizontal = 0.5;
				var result = FaustGong.ar(freq * pitchbend, gain, gate, initgain, rightHorizontal, rightVertical);
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

			// ~gutter = Synth(
			// 	defName: "Gutter",
			// 	//target: s,
			// 	addAction: 'addToHead'
			// );

		~organ = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Organ",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~vang = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Vang",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~testFMGrain = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "TestFMGrain",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~gutter = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Gutter",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~hihat = Synth(
				defName: "Hihat",
				//target: s,
				addAction: 'addToHead'
			);

			// ~mou = Synth(
			// 	defName: "Mou",
			// 	//target: s,
			// 	addAction: 'addToHead'
			// );

		~mou = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Mou",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];


		~testmou = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "TestMou",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~testmou2 = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "TestMou2",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~uan = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Uan",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~gong = [
				0,
				Array.fill( 4,
				{
					| value |
					var syn = Synth(
						defName: "Gong",
						//target: s,
						addAction: 'addToHead'
					);
					syn.run(false);
					syn
				}
			)
			];

		~synthsTable = [
			[ ~gong, ~mou,    ~testmou  ],
			[ ~gutter, ~uan, ~testmou2 ],
			[ ~organ, ~vang, ~testFMGrain ]
		];
			Langley.setcurrentSynth ( ~gong[1][0] );
			0.5.wait;
			~gong[1][0].run(true);
			~mou[1][0].run(true);
			~testmou[1][0].run(true);
			~testmou2[1][0].run(true);
			~gutter[1][0].run(true);
			~uan[1][0].run(true);
			~organ[1][0].run(true);
			~vang[1][0].run(true);
			~testFMGrain[1][0].run(true);
		}.play;
		}
}
