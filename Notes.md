# System Notes #

## Auditing ##

All messages both in a out of the system need to be recorded (preferably by a different process running a set of different physical hosts across multiple data centres).

## Monitoring ##

An ideal monitoring solution would be put in place such that any errors or problems/reduction in Quality of Service (QOS) are flagged via alters and can be investigated by RTB asap. Avoiding the situation where there are so many alerts that non of them get investigated in a must.

## Pluggable/Upgradable Modules ##

It would be very nice to be able to upgrade components of the systems in a rolling fashion, i.e. you need to make a change to the booking module so choose a quiet time, suspend booking, complete the release and restart booking, the rest of the system has continued to run during this time and any request to book during that time are queued and processed on restart.

## Reproducing/Replaying Events ##

It should be possible to boot up a server in any environment and replay events recorded by the auditing system or wiretap functionality that can replay all events allowing errors and production situations to be reproduced easily during a debugging session.

## Wiretapping ##

Asynchronously syphon each message to be processed off to disk where it should be stored for a week or two, message format should be fairly plain and easy to load and process should something need to be replayed (see above). This could be seen as equivalent to Auditing.

# Business Notes #

## Dangers ##

Both Jérome Kerviel (who lost €4.9bn (~$6.48bn) at Société Générale) and Kweku Adoboli ($2.3bn at UBS) were Delta One employees. Both were fairly junior traders and had knowledge from their respective banks back office and settlement systems.

## Resources ##

[Index Universe (EU)](http://www.indexuniverse.eu/)

## Settlement ##

Due to the nature of ETFs, settlement can be a complex and lengthy procedure, the complexity arises as a result of the network of European clearing and settlement systems, where trades in ETFs and equities are processed and where changes of ownership are recorded. These complexities mean ETF trades often fail to settle by the prescribed deadline (commonly T+3). For instance in London, a whole month can pass before a trader who fails to deliver an ETF gets fined - an ETF-specific loophole can also factor, where trades conducted between banks away from official exchanges are not always confirmed (ie, agreed) between the two parties right away.