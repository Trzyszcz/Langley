FaustGutter : MultiOutUGen
{
  *ar { | filres(1.0), freq(440.0), gain(0.3), gate(0.0), leftvertical(0.5), rightvertical(0.5) |
      ^this.multiNew('audio', filres, freq, gain, gate, leftvertical, rightvertical)
  }

  *kr { | filres(1.0), freq(440.0), gain(0.3), gate(0.0), leftvertical(0.5), rightvertical(0.5) |
      ^this.multiNew('control', filres, freq, gain, gate, leftvertical, rightvertical)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustGutter" }


  info { ^"Generated with Faust" }
}

