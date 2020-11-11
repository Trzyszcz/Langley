FaustTestMou2 : MultiOutUGen
{
  *ar { | freq(440.0), gain(0.3), gate(0.0), initgain(0.3), righthorizontal(0.5), rightvertical(0.5) |
      ^this.multiNew('audio', freq, gain, gate, initgain, righthorizontal, rightvertical)
  }

  *kr { | freq(440.0), gain(0.3), gate(0.0), initgain(0.3), righthorizontal(0.5), rightvertical(0.5) |
      ^this.multiNew('control', freq, gain, gate, initgain, righthorizontal, rightvertical)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustTestMou2" }


  info { ^"Generated with Faust" }
}

