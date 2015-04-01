///<reference path="../typings/angularjs/angular.d.ts" />
///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module Service {
    "use strict";

    export class AuthService {
        constructor($location:ng.ILocationService, $rootScope:any, $cookieStore:any, $http:ng.IHttpService) {
        }

        logout():void {
            // TODO
        }

        isLoggedIn():boolean {
            // TODO
            return true;
        }
    }
}