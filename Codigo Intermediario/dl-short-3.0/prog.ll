;LLVM version 3.8.0 (http://llvm.org/)
;program teste
declare i32 @printf(i8*, ...) nounwind
@str_print_int = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@str_print_double = private unnamed_addr constant [7 x i8] c"%.2lf\0A\00", align 1
define i32 @main() nounwind {
%a = alloca double
store double 0.0, double* %a
%b = alloca double
store double 0.0, double* %b
%1 = sitofp i32 2 to double
%2 = fsub double %1, 3.0
store double %2, double* %b
%3 = load double, double* %b
%4 = sitofp i32 4 to double
%5 = fmul double %3, %4
store double %5, double* %a
%6 = load double, double* %a
%7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([7 x i8], [7 x i8]* @str_print_double, i32 0, i32 0), double %6)
ret i32 0
}
