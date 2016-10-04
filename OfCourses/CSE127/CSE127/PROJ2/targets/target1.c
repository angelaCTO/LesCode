#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Bar takes in the first param and the char buffer then copies the contents
// of the param into the buffer. The overflow will happen here if we 
// pass in a char* string > 239 bytes
int bar(char *arg, char *out)
{
  strcpy(out, arg);
  return 0;
}

// Foo allocates a char buffer 239 bytes and the passes the  param
// and the buffer into bar 
int foo(char *argv[])
{
  char buf[239];
  bar(argv[1], buf);
}

// Takes in two params (the exec and a string) passes both of them in to foo
int main(int argc, char *argv[])
{
  if (argc != 2)
    {
      fprintf(stderr, "target1: argc != 2\n");
      exit(EXIT_FAILURE);
    }
  foo(argv);
  return 0;
}
