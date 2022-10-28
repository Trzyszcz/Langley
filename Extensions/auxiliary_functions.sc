+ Langley {
	/*In my gamepad controler, moving lef or right stick doesn't send HID message, up unit 0.56470590829849 value in the directon of 1, or 0.43529412150383 in the direction of zero. This produces a jump which is undesirable with control parameters like a pitchbend. Those functions are modifiing the values so changes in synth parameters are starting from 0.5. */
	*leveler { |x, p, q, fq| //0 for (-inf, p), linear on (p, q), fq for (q, inf)
		var k, m, ans;
		k = fq / (2*(q-p));
		m = fq / 2;
		ans = (k*abs(x-p)) - (k*abs(x-q)) + m;
		^ans;
	}

	*stand_leveler { |x| /*x should be between 0 and 1, maps (0, lcen) to (0, 0.5) linearly, (lcen, rcen)
		to 0.5, and (rcen, 1) to (0, 0.5) linearly */
		var ans;
		ans = Langley.leveler(x, 0, Langley.lcen, 0.5) + Langley.leveler(x, Langley.rcen, 1, 0.5);
		^ans;
	}
}