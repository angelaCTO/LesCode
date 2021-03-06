(* CSE 130: Programming Assignment 3
  * Name: Angela To
  * PID: A10657395
  * Date: 10/19/15
 *)
 
 
 (* Do not change the skeleton code! The point of this assignment is to figure
  * out how the functions can be written this way (using fold). You may only
  * replace the   failwith "to be implemented"   part. *)
 
 
 (*****************************************************************)
 (******************* 1. Warm Up   ********************************)
 (*****************************************************************)
 (* SQSUM : int list -> int
  * x1^2 + x2^2 + ... + xn^2
 *)
 let sqsum xs = 
   let f a x = x * x + a in
   let base  = 0 in
     List.fold_left f base xs ;;
 
 let _ = sqsum [] ;;
 let _ = sqsum [1;2;3;4] ;;
 let _ = sqsum [(-1); (-2); (-3); (-4)] ;;
 
 
 
 
 (* PIPE : ('a -> 'a) list -> ('a -> 'a)
  * pipe[f1, ... , fn] should return a function f such that for any x
  * we have f x return fn(.. (f2 (f1 x) x) x)
  * pipe[f1, ... , fn] -> f x -> fn(.. (f2 (f1 x) x) x)
 *)
 let pipe fs =
   let f a x = fun x' ->  x(a x') in
   let base  = fun x  -> x in
     List.fold_left f base fs ;;
 
 let _ = pipe [] 3 ;;
 let _ = pipe [(fun x -> x+x); (fun x -> x + 3)] 3 ;;
 let _ = pipe [(fun x -> x + 3);(fun x-> x + x)] 3 ;;
 
 
 
 
 (* SEPCAT : 
  * sepCat sep [s1; s2; ... ; sn] where sep is the delimiter and 
  * s1 .. sn are strings.  
  * *)
 let rec sepConcat sep sl = 
   match sl with 
     | [] -> ""
     | h :: t -> 
         let f a x = a ^ sep ^ x in
         let base  = h in
         let l     = t in
           List.fold_left f base l ;; 
 
 let _ = sepConcat ", " ["foo";"bar";"baz"] ;;
 let _ = sepConcat "---" [] ;;
 let _ = sepConcat "" ["a";"b";"c";"d";"e"] ;;
 let _ = sepConcat "X" ["hello"] ;;
 
 
 
 
 (* STRINGOFLIST : 
  * stringOfList f [x1; x2; x3] should return the string 
  * "[" ^ (f x1) ^ "; " ^ ... ^ (f xn) ^ "]"
  * This function should be implemented on one line, without using any 
  * recursion by calling List.map and sepCat with appropriate inputs
  * *)
 let stringOfList f l = "[" ^ (sepConcat "; " (List.map f l)) ^ "]" ;;
 
 let _ = stringOfList string_of_int [1;2;3;4;5;6] ;;
 let _ = stringOfList (fun x -> x) ["foo"] ;;
 let _ = stringOfList (stringOfList string_of_int) [[1;2;3];[4;5];[6];[]] ;;
 
 
 
 
 (*****************************************************************)
 (******************* 2. Big Numbers ******************************)
 (*****************************************************************)
 
 (* CLONE : list 
  * Curried function clone x n returns a list of n copies of x *)
 let rec clone x n = 
   if n <= 0 then []
   else x :: clone x (n-1) ;; 
 
 let _ = clone 3 5 ;;
 let _ = clone "foo" 2 ;; 
 let _ = clone clone (-3) ;;
 
 
 
 
 (* PADZERO : list
  * Use clone to write curried function padzero which takes in two 
  * lists, [x1; x2; x3 ...] and [y1; y2; y3; ...] and adds zeroes
  * to the shorter list to make the lists equal (in length)
  * *)
 let padZero l1 l2 = 
   let l1_len = List.length l1 in
   let l2_len = List.length l2 in
     	if l1_len > l2_len then 
       let zeros = clone 0 (l1_len - l2_len) in
         (l1, zeros @ l2)
     else if l1_len < l2_len then 
       let zeros = clone 0 (l2_len - l1_len) in
         (zeros @ l1, l2)
     else (l1, l2) ;;
 
 let _ = padZero [9;9] [1;0;0;2] ;;
 let _ = padZero [1;0;0;2] [9;9] ;;
 
 
 
 
 (* REMOVEZERO : list
  * removeZero should take a list and strips away a prefix of leading zeroes 
  * *)
 let rec removeZero l = 
   	match l  with
     	| []     -> []
     | h :: t ->
         match h with
           | 0 -> removeZero t
           | _ -> l ;; 
 
 let _ = removeZero [0;0;0;1;0;0;2] ;;
 let _ = removeZero [9;9] ;;
 let _ = removeZero [0;0;0;0] ;;
 
 
 
 
 (* BIGADD : int list 
  * Takes two integer lists, where each integer is between 0 and 9 and 
  * returns the list corresponding to the addition of the two big-integers.
  *)
 let bigAdd l1 l2 =
   	let add (l1,l2) =
     let f a x =
       match a with
         | (carried, tot) -> 
             match x with
               | (x1, x2) -> 
                   let  sum = x1 + x2 + carried in
                     (sum / 10, (sum mod 10) :: tot) in
     let base = (0,[]) in
     (* Prepend each list with 0 to capture last carry 
      * if exists *)
     let args = List.rev (List.combine (0::l1) (0::l2)) in
     let (_,res) = (List.fold_left f base args) in
       res in
     	removeZero(add(padZero l1 l2)) ;;
 
 let _ = bigAdd [9;9] [1;0;0;2] ;;
 let _ = bigAdd [9;9;9;9] [9;9;9] ;; 
 
 
 
 
 (* MULTBYDIGIT : int list 
  * Takes an integer digit and a big integer, and returns the big 
  * integer list which is the result of multiplying the big integer 
  *with the digit
  *)
 let rec mulByDigit i l = 
   	let f a x = 
     match i with
       | 1 -> l
       | _ -> let product = i * x in 
             match a with
               | h::t -> ((h + product) / 10) :: ((h + product) mod 10) :: t
               | _    -> (product / 10) :: [product mod 10] in
   let base = [] in
     removeZero(List.fold_left f base (List.rev l)) ;;
 
 let _ = mulByDigit 9 [9;9;9;9] ;;
 
 
 
 
 (* BIGMULT : int list 
  * Takes two integer lists, where each integer is between 0 and 9
  * and returns the list corresponding to the product of the two 
  * big-integers 
  *)
 let bigMul l1 l2 =
   	let f a x =
     match a with
       | (i, tot) ->
           match x with
             | (_, x') ->
                 let res = bigAdd (mulByDigit x' l1 @ clone 0 i) tot in
                   (i + 1, res) in
   let base = (0, []) in
   let args = List.combine (clone l1 (List.length l2)) (List.rev l2) in
   let (_, res) = List.fold_left f base args in
     res ;;
 
 let _ = bigMul [9;9;9;9] [9;9;9;9] ;;
 let _ = bigMul [9;9;9;9;9] [9;9;9;9;9] ;; 
 
 
 
 
 (*******************************************************************************)
 (******************** DO NOT MODIFY ANYTHING AFTER THIS ************************)
 (*******************************************************************************)
 
 


 (* CSE 130 PA 3. Autotester *)
 
 let key = "" (* change *)
 let prefix130 = "130" (* change *)
 let print130 s = print_string (prefix130^">>"^s)
 
 exception ErrorCode of string
 
 type result = Pass | Fail | ErrorCode of string
 
 let score = ref 0
 let max = ref 0
 let timeout = 300
 
 let runWTimeout (f,arg,out,time) = 
   try if compare (f arg) out = 0 then Pass else Fail
   with e -> (print130 ("Uncaught Exception: "^(Printexc.to_string e)^"\n"); ErrorCode "exception") 
 
 exception TestException
 let testTest () =
   let testGood x = 1 in
   let testBad x = 0 in 
   let testException x = raise TestException in
   let rec testTimeout x = testTimeout x in
     runWTimeout(testGood,0,1,5) = Pass &&  
     runWTimeout(testBad,0,1,5) = Fail &&  
     runWTimeout(testException,0,1,5) = ErrorCode "exception" && 
     runWTimeout(testTimeout,0,1,5) = ErrorCode "timeout"
 
 
 let runTest (f,arg,out,points,name) =
   let _ = max := !max + points in
   let outs = 
     match runWTimeout(f,arg,out,timeout) with 
         Pass -> (score := !score + points; "[pass]")
       | Fail -> "[fail]"
       | ErrorCode e -> "[error: "^e^"]"  in
     name^" "^outs^" ("^(string_of_int points)^")\n"
 
 (* explode : string -> char list *)
 let explode s = 
   let rec _exp i = 
     if i >= String.length s then [] else (s.[i])::(_exp (i+1)) in
     _exp 0
 
 let implode cs = 
   String.concat "" (List.map (String.make 1) cs)
 
 let drop_paren s = 
   implode (List.filter (fun c -> not (List.mem c ['(';' ';')'])) (explode s))
 
 let eq_real p (r1,r2) = 
   (r1 -. r2) < p || (r2 -. r1) < p
 
 let wrap_curried_2 f (a,b) = f a b
 
 let runAllTests () =
   let _ = (score := 0; max := 0) in
   let report = 
     [runTest (sqsum, [], 0, 1, "sqsum 1");
      runTest (sqsum, [1;2;3;4], 30, 1, "sqsum 2");
      runTest (sqsum, [-1;-2;-3;-4], 30, 1, "sqsum 3");
 
      runTest (wrap_curried_2 pipe, ([], 3), 3, 1, "pipe 1");
      runTest (wrap_curried_2 pipe, ([(fun x-> 2*x);(fun x -> x + 3)], 3), 9, 1, "pipe 2");
      runTest (wrap_curried_2 pipe, ([(fun x -> x + 3); (fun x-> 2*x)], 3), 12, 1, "pipe 3");
 
      runTest(wrap_curried_2 sepConcat, (", ",["foo";"bar";"baz"]), "foo, bar, baz", 1, "sepConcat 1");
      runTest(wrap_curried_2 sepConcat, ("---",[]), "", 1, "sepConcat 2");
      runTest(wrap_curried_2 sepConcat, ("",["a";"b";"c";"d";"e"]), "abcde", 1, "sepConcat 3");
      runTest(wrap_curried_2 sepConcat, ("X",["hello"]), "hello", 1, "sepConcat 4");
 
      runTest(wrap_curried_2 stringOfList, (string_of_int,[1;2;3;4;5;6]), "[1; 2; 3; 4; 5; 6]",1,"stringOfList 1");
      runTest(wrap_curried_2 stringOfList, ((fun x -> x),["foo"]), "[foo]",1,"stringOfList 2");
      runTest(wrap_curried_2 stringOfList, ((stringOfList string_of_int),[[1;2;3];[4;5];[6];[]]), "[[1; 2; 3]; [4; 5]; [6]; []]",1,"stringOfList 3");
 
      runTest(wrap_curried_2 clone, (3,5), [3;3;3;3;3],1,"clone 1");
      runTest(wrap_curried_2 clone, ("foo",2), ["foo";"foo"],1,"clone 2");
      runTest(wrap_curried_2 clone, (clone,-3), [],1,"clone 3");
 
      runTest(wrap_curried_2 padZero, ([9;9],[1;0;0;2]), ([0;0;9;9],[1;0;0;2]),1,"padzero 1");
      runTest(wrap_curried_2 padZero, ([1;0;0;2],[9;9]), ([1;0;0;2],[0;0;9;9]),1,"padzero 2");
 
      runTest(removeZero, [0;0;0;1;0;0;2], [1;0;0;2],1,"removeZero 1");
      runTest(removeZero, [9;9], [9;9],1,"removeZero 2");
 
      runTest(wrap_curried_2 bigAdd,  ([9;9],[1;0;0;2]), [1;1;0;1],1, "bigAdd 1");
      runTest(wrap_curried_2 bigAdd,  ([9;9;9;9],[9;9;9]), [1;0;9;9;8],1, "bigAdd 2");
 
      runTest(wrap_curried_2 mulByDigit,  (9,[9;9;9;9]), [8;9;9;9;1],1, "mulByDigit 1");
 
      runTest(wrap_curried_2 bigMul,  ([9;9;9;9],[9;9;9;9]), [9;9;9;8;0;0;0;1],1, "bigMul 1");
      runTest(wrap_curried_2 bigMul,  ([9;9;9;9;9],[9;9;9;9;9]), [9;9;9;9;8;0;0;0;0;1],1,"bigMul 2");
     ] in
   let s = Format.sprintf "Results: Score/Max = %d / %d \n" !score !max in
   let _ = List.iter print130 (report@([s])) in
     (!score,!max)
 
 let _ = runAllTests ()
 
 let _ = print130 ("Compiled"^key^"\n")
