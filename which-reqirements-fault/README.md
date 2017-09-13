## Question

The situation is:
 - Module A requires `contract`
 - Module B provides `contract` and requires `missingContract` not provided by any module!
 - Module C provides `contract` and requires `anotherMissingContract` not provided by any module!
 - User wants to resolve module A

This is obviously NOT going to resolve! The question is what is the resolver going to report? Unresolved because of B or C?

## Answer

It seams when more than one module satisfies given requirement, the resolver picks the last one in alphabetical order. In this case both `B` and `C` satisfy `contract` so `C` is reported as the one that can not be resolved. If we change `Bundle-SymbolicName` of `C` to `A_bundle_name_starting_with_A`, then `B` is reported as the one that can not be resolved.

## More questions

 - What is the proper way to inform the user that there are multiple options to fix the problem? In this particular case for example there are 2 solutions - satisfy B's requirements or satisfy A's requirements ) ?