FaustOrgan : MultiOutUGen
{
  *ar { | leftpush(0.0), rightpush(0.0), freq(440.0), gain(0.3), gate(0.0), leftvertical(0.5), rightvertical(0.5) |
      ^this.multiNew('audio', leftpush, rightpush, freq, gain, gate, leftvertical, rightvertical)
  }

  *kr { | leftpush(0.0), rightpush(0.0), freq(440.0), gain(0.3), gate(0.0), leftvertical(0.5), rightvertical(0.5) |
      ^this.multiNew('control', leftpush, rightpush, freq, gain, gate, leftvertical, rightvertical)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustOrgan" }


  info { ^"Generated with Faust" }
}

