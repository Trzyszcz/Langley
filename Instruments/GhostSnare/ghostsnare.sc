FaustGhostsnare : MultiOutUGen
{
  *ar { | rightpush(0.0), frequency(220.0), gain(1.0), gate(0.0), lowpassfreq(2000.0), resonq(6.0) |
      ^this.multiNew('audio', rightpush, frequency, gain, gate, lowpassfreq, resonq)
  }

  *kr { | rightpush(0.0), frequency(220.0), gain(1.0), gate(0.0), lowpassfreq(2000.0), resonq(6.0) |
      ^this.multiNew('control', rightpush, frequency, gain, gate, lowpassfreq, resonq)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustGhostsnare" }


  info { ^"Generated with Faust" }
}

