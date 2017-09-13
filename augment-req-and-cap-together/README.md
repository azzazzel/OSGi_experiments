## Question

The situation is:
 - Module A requires `contract1`
 - Module B has no requirements nor capabilities!
 - Module C provides `contract2` 
 - User wants to augment module B so that it provides `contract1` and requires `contract2` at the same time

## Answer

Based on experiments below, it seems it's not possible to achieve that with bnd-export-maven-plugin 3.4.0 


#### First make sure augmenting works

 - Augment B to provide `contract1` **works** - both A and B are exported by these instructions! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/1_A_req_B.bndrun)

	-augment.B: \
		augment-req-and-cap-together-B; \
			capability:="contract1"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-A)'

 - Augment B to require `contract2` **works** - both B and C are exported by these instructions! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/1_B_req_C.bndrun) 

	-augment.B: \
		augment-req-and-cap-together-B; \
			requirement:="contract2"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-B)'

#### Attempts to augment B to both provide `contract1` and require `contract2` at the same time

 - Adding capability and requirement _(in that order)_ **doesen't work**! These instructions were expected to get A, B and C exported but only A and B are exported! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/3_1_cr_A_req_B_req_C.bndrun) 

	-augment.B: \
		augment-req-and-cap-together-B; \
			capability:="contract1", \
			requirement:="contract2"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-A)'

 - Adding requirement and capability _(in that order)_ **doesen't work**! These instructions were expected to get A, B and C exported but resolver fails! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/3_1_rc_A_req_B_req_C.bndrun) 

	-augment.B: \
		augment-req-and-cap-together-B; \
			requirement:="contract2", \
			capability:="contract1"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-A)'

 - Adding capability and requirement _(in that order)_ as separate instructions **doesen't work**! These instructions were expected to get A, B and C exported but only A and B are exported! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/3_2_cr_A_req_B_req_C.bndrun) 

	-augment.B.cap: \
		augment-req-and-cap-together-B; \
			capability:="contract1"
	
	-augment.B.req: \
		augment-req-and-cap-together-B; \
			requirement:="contract2"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-A)'

 - Adding requirement and capability _(in that order)_ as separate instructions **doesen't work**! These instructions were expected to get A, B and C exported but only A and B are exported! [(see actual experiment's bndrun file)](augment-req-and-cap-together-run/3_2_rc_A_req_B_req_C.bndrun) 

	-augment.B.req: \
		augment-req-and-cap-together-B; \
			requirement:="contract2"
	
	-augment.B.cap: \
		augment-req-and-cap-together-B; \
			capability:="contract1"
	
	-runrequires: osgi.identity;filter:='(osgi.identity=augment-req-and-cap-together-A)'


	