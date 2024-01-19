#include<stdio.h>
#include<string.h>

char prod[3][15] = {"A->aBa", "B->bB", "B->@"};
char table[2][3][4] = {{"aBa", " ", " "}, {"@", "bB", " "}};
int size[2][3] = {{3, 0, 0}, {1, 2, 0}};
int n;
char s[20], stack[20];

void display(int i, int j) {
    int k;
    for (k = 0; k <= i; k++)
        printf("%c", stack[k]);
    printf("\t");
    for (k = j; k < n; k++)
        printf("%c", s[k]);
    printf("\n");
}

int main() {
    int i, j, k, row, col;
    printf("\n The grammar is:\n");
    for (i = 0; i < 3; i++)
        printf("%s\n", prod[i]);
    
    printf("\n Predicting parsing table is:\n\n");
    printf("\t\ta\tb\t$\n");
    printf("----------------------------------\n");
    for (i = 0; i < 2; i++) {
        if (i == 0)
            printf("A");
        else
            printf("\nB");
        
        for (j = 0; j < 3; j++) {
            printf("\t%s", table[i][j]);
        }
    }
    
    printf("\n\nEnter the input string: ");
    scanf("%s", s);
    
    printf("\nStack\t\tInput\n");
    printf("----------------------------------\n");
    printf("$A\t\t%s$\n", s);
    
    strcat(s, "$");
    n = strlen(s);
    stack[0] = '$';
    stack[1] = 'A';
    i = 1;
    j = 0;
    
    while (1) {
        if (stack[i] == s[j]) {
            i--;
            j++;
            
            if (stack[i] == '$' && s[j] == '$') {
                printf("$\t\t$\nSUCCESS\n");
                break;
            } else if (stack[i] == '$' && s[j] != '$') {
                printf("ERROR\n");
                break;
            }
            
            display(i, j);
        }
        
        switch (stack[i]) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
        }
        
        switch (s[j]) {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case '$':
                col = 2;
                break;
            default:
                printf("ERROR\n");
                return 0;
        }
        
        if (table[row][col][0] == '\0') {
            printf("ERROR\n");
            break;
        } else if (table[row][col][0] == '@') {
            i--;
            display(i, j);
        } else {
            for (k = size[row][col] - 1; k >= 0; k--) {
                stack[i] = table[row][col][k];
                i++;
            }
            
            i--;
            display(i, j);
        }
    }
    
    return 0;
}
