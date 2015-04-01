module Filter {
    "use strict";

    export function ViewingSortFilter() {
        return (input:any):any => {
            if (!input) {
                //不正な入力値の場合、引数をそのまま返す
                return input;
            }

            input.sort((a:any, b:any) => {
                if (a.status < b.status) {
                    return 1;
                } else if (b.status < a.status) {
                    return -1;
                } else {
                    return 0;
                }
            });

            return input;
        };
    }
}
