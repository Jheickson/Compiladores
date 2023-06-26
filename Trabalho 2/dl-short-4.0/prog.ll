;LLVM version 3.8.0 (http://llvm.org/)
;program teste
declare i32 @printf(i8*, ...) nounwind
@str_print_int = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@str_print_double = private unnamed_addr constant [7 x i8] c"%.2lf\0A\00", align 1
define i32 @main() nounwind {
%a = alloca i32
store i32 0, i32* %a
%b = alloca i32
store i32 0, i32* %b
%c = alloca i32
store i32 0, i32* %c
%d = alloca i32
store i32 0, i32* %d
%e = alloca i32
store i32 0, i32* %e
store i32 19, i32* %a
store i32 1, i32* %b
store i32 4999, i32* %c
%1 = load i32, i32* %a
%2 = load i32, i32* %b
%3 = add i32 %1, %2
store i32 %3, i32* %d
%4 = load i32, i32* %a
%5 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %4)
%6 = load i32, i32* %b
%7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %6)
%8 = load i32, i32* %c
%9 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %8)
%10 = load i32, i32* %d
%11 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %10)
%12 = load i32, i32* %c
%13 = add i32 %12, 1
store i32 %13, i32* %e
%14 = load i32, i32* %e
%15 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %14)
ret i32 0
}
