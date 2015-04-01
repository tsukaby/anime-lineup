/**
 * モデルのモジュール。
 */
module Model {
    "use strict";

    export class Animator {
        animatorId:number;
        name:string;
        wikipediaSiteUrl:string;
    }

    export class Company {
        companyId:number;
        name:string;
        companyType:number;
    }

    export class Anime {
        animeId:number;
        name:string;
        year:number;
        seasonType:number;
        productionCompanyId:number;
        directorId:number;
        originalPiece:string;
        officialSiteUrl:string;
        wikipediaSiteUrl:string;
        director:Animator;
        productionCompany:Company;

        getThumbnailURL():string {
            return "/thumbs/" + this.officialSiteUrl;
        }

        getHatebuURL():string {
            return "http://b.hatena.ne.jp/entry/" + this.officialSiteUrl;
        }

        getFacebookButtonURL():string {
            return "https://www.facebook.com/plugins/like.php?href=" + this.officialSiteUrl + "&width&layout=box_count&action=like&show_faces=false&share=false&height=65&appId=215921371931439";
        }
    }

    export class Season {
        year:number;
        seasonType:number;

        constructor(year:number, seasonType:number) {
            this.year = year;
            this.seasonType = seasonType;
        }
    }
}
