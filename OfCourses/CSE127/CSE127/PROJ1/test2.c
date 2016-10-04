#include <stdio.h>
#include <stdlib.h>

int cmpfunc (const void* a, const void* b) {
	return ( *(unsigned long*)a - *(unsigned long*)b );
}

int int_cmp(const void *a, const void *b) {
	return *(unsigned long *)a - *(unsigned long *)b;
}

/*
float compute_median(long *arr, int arr_len) {
	int i;
	for(i=0; i < arr_len; i++) {
		printf("%lu ", arr[i]);
	}

	long median = 0;
	qsort(arr, arr_len, sizeof(unsigned long), cmpfunc);

	printf("%s: \n", "Sorted");
	for(i=0; i < arr_len; i++) {
		printf("%lu ", arr[i]);
	}
	
	if (arr_len % 2 == 0) {
		float a = arr[(arr_len/2)];
		float b = arr[(arr_len/2-1)];
		float m = (a + b) / 2.0;
		return(m);
	} else {
		int m = floor(arr_len/2);
		return(arr[m]);
	}
}
*/

float comp_med2(long *arr, int arr_len) {
	int i;
	for(i=0; i < arr_len; i++) {
		printf("%lu ", arr[i]);
	}
	printf("\n%s\n", "SORTED");
	
	qsort(arr, arr_len, sizeof(unsigned long), cmpfunc);

	int j;
	for(j=0; j < arr_len; j++) {
		printf("%lu ", arr[j]);
	}
	
	if (arr_len % 2 == 0 ) {
		float m1 = (float)(arr[arr_len/2] + (float)arr[arr_len/2 -1]) / 2.0;
		printf("MEDIAN(2): %f\n", m1);
	} else {
		float m2 = arr[arr_len/2];
		printf("MEDIAN(1): %f\n", m2);
	}	
}





int main(void) {

	long arr[19] = {0,0,0,0,10,9,8,7,6,5,4,3,2,1,0,12, 31, 23,0};
//	float med = compute_median(arr, 16);

	float m = comp_med2(arr, 19);
//	printf("\nMEDIAN: %f\n", m);
	return 0;
}
