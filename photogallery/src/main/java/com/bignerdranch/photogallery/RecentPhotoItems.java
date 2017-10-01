package com.bignerdranch.photogallery;

import java.util.List;

/**
 * Created by hongy_000 on 2017/9/25.
 */

public class RecentPhotoItems {

    /**
     *
     */

    private PhotosBean photos;
    private String stat;

    public PhotosBean getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosBean photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public static class PhotosBean {
        /**
         * page : 1
         * pages : 10
         * perpage : 100
         * total : 1000
         * photo : [{"id":"36423853254","owner":"158940018@N08","secret":"cec4f04862","server":"4346","farm":5,"title":"What's app images","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423853334","owner":"65258641@N00","secret":"efbe55e582","server":"4335","farm":5,"title":"SMAK","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423855584","owner":"98268377@N05","secret":"af8a4893d9","server":"4368","farm":5,"title":"#SaborAMexico","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423862534","owner":"147800673@N04","secret":"629e63aa69","server":"4416","farm":5,"title":"\"A ship is safe in harbour, but that's not what ships are for.\" - William Shedd#","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423863674","owner":"148207523@N07","secret":"a99efdb4fd","server":"4422","farm":5,"title":"Snappy the Dragonfly","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423866034","owner":"59071756@N03","secret":"e5cd54b8d1","server":"4441","farm":5,"title":"DSCF2410","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423866324","owner":"153322875@N05","secret":"7b4e2bb03e","server":"4393","farm":5,"title":"Phoenix-000006-T1-20170525120703260517396","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423868314","owner":"40119054@N08","secret":"4381d27bc3","server":"4354","farm":5,"title":"Maze Cave, Beit Guvrin-Maresha National Park, Israel, September 2017 #iphoneonly #vsco #instagram","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423872474","owner":"143884523@N02","secret":"c78603e7c2","server":"4427","farm":5,"title":"Sand Filled Astroturf Maintenance in Glasgow City #Sand #Filled...","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423872714","owner":"157259199@N03","secret":"6e4f2ece5d","server":"4442","farm":5,"title":"59bd3e338a763.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423873944","owner":"73827808@N00","secret":"2f0dce1f6c","server":"4415","farm":5,"title":"Quiet ad quiter through the evening","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423875144","owner":"80048637@N02","secret":"1e4d2ba544","server":"4357","farm":5,"title":"#StreetArt Tiny Mr. Pee, Paris, 4th arrondissement [3089x2048] (OC)","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423877634","owner":"30807163@N00","secret":"60d0993504","server":"4340","farm":5,"title":"image","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423878134","owner":"43998850@N05","secret":"bc3453ea76","server":"4427","farm":5,"title":"怕咗你地喇，嗱嗱嗱，Iphone7plus殼啊～！😎😎😎 -------- Repost @hall1cshop ： 【Camp to Home \u2014 最新推出】 📣📣📣用Plus嘅朋友，你地有得用喇！ \"Camp to Home\" iPhone 7 PLUS case！* 此產品只推出少量，大家欲購從速喇！ Buy at hall1c.com","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423879014","owner":"51073284@N05","secret":"068931ca7f","server":"4347","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423879724","owner":"158664507@N06","secret":"17b7e6d059","server":"4377","farm":5,"title":"How to Choose the Best Milk Substitute for You","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423879844","owner":"68144590@N00","secret":"7374192732","server":"4348","farm":5,"title":"IMG_0033","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423881924","owner":"71202578@N04","secret":"b7a402e9d4","server":"4379","farm":5,"title":"Our last day art Euro Disney","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423882034","owner":"157388868@N06","secret":"6644bb37c1","server":"4384","farm":5,"title":"Getting His Good Side","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36423882444","owner":"17886179@N00","secret":"34338065b1","server":"4345","farm":5,"title":"MID-NIGHT FLOWER","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447073783","owner":"154155852@N02","secret":"42e4de1e34","server":"4427","farm":5,"title":"Leakin Park parkrun Sept 16 part 2","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447074923","owner":"10594207@N04","secret":"959b81718c","server":"4394","farm":5,"title":"2017-09-16_11-06-31","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447075293","owner":"97569677@N05","secret":"5420acf07f","server":"4412","farm":5,"title":"L1389949.1","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447076583","owner":"70653371@N05","secret":"a74cb2d81c","server":"4388","farm":5,"title":"Apple watch","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447078003","owner":"92332831@N04","secret":"e6a3433fbc","server":"4440","farm":5,"title":"Фред очень рад вызову в сборную Бразилии","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447078813","owner":"151950294@N06","secret":"218fdbea23","server":"4366","farm":5,"title":"Gainesville FL 🐊🐊's game","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447079363","owner":"38993937@N05","secret":"a3860c49ba","server":"4335","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447081063","owner":"12220879@N06","secret":"8a679d66e4","server":"4401","farm":5,"title":"Baby's first 'spro.","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447085543","owner":"29594259@N00","secret":"68f5b3b178","server":"4345","farm":5,"title":"28th August 2016 227","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447085633","owner":"83634602@N02","secret":"d17d10fa97","server":"4404","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447088443","owner":"152820766@N06","secret":"00abfc6757","server":"4370","farm":5,"title":"Pasta roast fish for today. . . . . #dinner #photography #xiaomiphotography #saturdaynight","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447091263","owner":"66833904@N00","secret":"bc7e3e4198","server":"4353","farm":5,"title":"NUS","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447091403","owner":"156617899@N08","secret":"90dcbe0636","server":"4341","farm":5,"title":"DSC03634","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447093853","owner":"15450269@N03","secret":"1866b629c8","server":"4344","farm":5,"title":"_GB71095","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447094233","owner":"152127725@N05","secret":"646b007c2c","server":"4427","farm":5,"title":"20150206 A6.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447094363","owner":"59209156@N04","secret":"3360a0d2b9","server":"4382","farm":5,"title":"Pie dish.","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36447095253","owner":"138215572@N08","secret":"6ee591be26","server":"4393","farm":5,"title":"20170916_084802","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863565080","owner":"150895130@N07","secret":"a458613991","server":"4367","farm":5,"title":"#electricblueconverse","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863568810","owner":"152820737@N08","secret":"f989a278d9","server":"4337","farm":5,"title":"IMG_20170216_140640~2","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863569230","owner":"12639178@N07","secret":"ee368f5bc8","server":"4364","farm":5,"title":"Gefleckte Schwarznesselwanze im Freizeitpark Marienfelde, Berlin , NGIDn2060115317","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863569640","owner":"101726164@N02","secret":"200d8dcc09","server":"4342","farm":5,"title":"Da Lat - Ninh Thuan - Nha Trang Trip","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863569710","owner":"151939951@N07","secret":"3d8fa5ef81","server":"4436","farm":5,"title":"搞怪","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863570810","owner":"141074106@N08","secret":"0d45036849","server":"4371","farm":5,"title":"IMG_7279","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863571120","owner":"110870145@N06","secret":"12a4b7cc89","server":"4382","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863573230","owner":"7250827@N06","secret":"dbbfa63bfc","server":"4427","farm":5,"title":"Randonnée à la Dent de Crolles","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863574280","owner":"144990765@N04","secret":"127918f60f","server":"4397","farm":5,"title":"Durante muito tempo eu acreditei que estar no curso me manteria ativo fotograficamente. Estava ansioso e ao mesmo tempo preocupado em saber o que aconteceria depois que o curso acabasse. Nada mudou. ᴛʜᴇ ᴇɴᴅ","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863575900","owner":"153691391@N02","secret":"77d0fc57f8","server":"4364","farm":5,"title":"Finland / Kerava","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863576800","owner":"115472973@N02","secret":"677c78d8f1","server":"4346","farm":5,"title":"The non barking dogs","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863577340","owner":"143528149@N08","secret":"2a910fae09","server":"4358","farm":5,"title":"Maintenance of Long Jump Runway in Lyne Station #Long #Jump...","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863579880","owner":"152255434@N06","secret":"50677e6f3b","server":"4437","farm":5,"title":"2016_2017( half)","ispublic":1,"isfriend":0,"isfamily":0},{"id":"36863581190","owner":"23994647@N06","secret":"65b1e34e78","server":"4360","farm":5,"title":"_DSC6088","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071363006","owner":"135530201@N07","secret":"baa9d6f63e","server":"4377","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071363946","owner":"14249179@N02","secret":"1227497505","server":"4422","farm":5,"title":"20170914-IMG_3595.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071364816","owner":"111198415@N05","secret":"329691ffc2","server":"4339","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071365266","owner":"152253944@N06","secret":"c56bcd53b8","server":"4381","farm":5,"title":"Whiteley parkrun #24 16-September-2017","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071367146","owner":"92033577@N00","secret":"eca95de30e","server":"4433","farm":5,"title":"\"M\" by Fletcher Benton. At the Oakland Museum of California.","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071367366","owner":"148068087@N06","secret":"11ba88082e","server":"4333","farm":5,"title":"VALENCIA -- SPAIN","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37071369206","owner":"159281058@N08","secret":"4ebd271dcf","server":"4431","farm":5,"title":"e63b4b4ec39a3f28e284d052023146f4","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37089528892","owner":"67109307@N02","secret":"95ee8e971d","server":"4420","farm":5,"title":"ktw_napraten_prijsuitreiking_0211.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37089530332","owner":"46409665@N03","secret":"f2eb6529df","server":"4347","farm":5,"title":"Sersheim-18157.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37089531802","owner":"152231915@N08","secret":"9cedf36077","server":"4389","farm":5,"title":"OLYMPUS DIGITAL CAMERA","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37089532402","owner":"129526591@N03","secret":"53b05575a2","server":"4399","farm":5,"title":"DSC_9306.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119148571","owner":"36148977@N00","secret":"7b0c32871a","server":"4354","farm":5,"title":"Leicester","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119150501","owner":"144565070@N02","secret":"d4d397854f","server":"4336","farm":5,"title":"Turkije 2017","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119151851","owner":"148453334@N03","secret":"76099010dd","server":"4419","farm":5,"title":"received_1824436287571075","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119154781","owner":"137694895@N05","secret":"67cfb1cd23","server":"4424","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119154921","owner":"148101302@N05","secret":"9fda8ea32b","server":"4429","farm":5,"title":"DSC_5107.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119155901","owner":"150965052@N06","secret":"e3300452d0","server":"4415","farm":5,"title":"Palm tree","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119157201","owner":"154703619@N03","secret":"8c2681123a","server":"4392","farm":5,"title":"Muar-140","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119157341","owner":"41864014@N03","secret":"58b30c91f8","server":"4431","farm":5,"title":"Etteridge _ Model Home _  Sparks II _ C _ Westin Home _ Travis Edmunds _ Photography _ Realtor _ www.TravisRE (11)","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119157601","owner":"152696560@N03","secret":"39a698e6a0","server":"4340","farm":5,"title":"New post on souhailbog","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37119158681","owner":"157580749@N03","secret":"c7fba4def2","server":"4419","farm":5,"title":"Chirk Castle","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261796735","owner":"50672337@N08","secret":"3d6e40822a","server":"4356","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261802785","owner":"142205286@N05","secret":"75fb53d99d","server":"4357","farm":5,"title":"Orlando 2017","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261803275","owner":"153120416@N07","secret":"cbb2e8c55e","server":"4360","farm":5,"title":"2017-09-16_05-05-28","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261803855","owner":"151301191@N04","secret":"f7ef39fbac","server":"4361","farm":5,"title":"Recette Minceur : Lasagnes aux Haricots Verts -...","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261804125","owner":"109563742@N02","secret":"8df5987612","server":"4392","farm":5,"title":"DSC07513","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261805515","owner":"8879190@N06","secret":"fcd9c56f02","server":"4331","farm":5,"title":"大阪","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261807405","owner":"154823962@N04","secret":"e0df718958","server":"4388","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261808565","owner":"149058190@N04","secret":"5b97e7c4ca","server":"4339","farm":5,"title":"vE6pNn","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261809995","owner":"84515585@N08","secret":"ee3b9d40e4","server":"4361","farm":5,"title":"New photo added to gallery","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261810905","owner":"106107547@N06","secret":"894a9fe29b","server":"4416","farm":5,"title":"Balbuzard pêcheur","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261811425","owner":"27479213@N08","secret":"b363545ce6","server":"4434","farm":5,"title":"IMG_5606","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261811655","owner":"44545248@N00","secret":"4652cfce42","server":"4425","farm":5,"title":"THE SHI(F)T-WORKING MUM // Whilst I have been hunkered down with FluB, I have blogged, and \"rebranded\". Surely there are other shi(f)t working mamas out there? Or even people who just want to laugh, or procrastibake? Come over, read the story, and say g'd","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261815445","owner":"70294878@N08","secret":"6a5c81cab0","server":"4392","farm":5,"title":"IMG_5239.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261818595","owner":"154985333@N04","secret":"357f5f8f6d","server":"4403","farm":5,"title":"Kristen Wiig Nude \u2013 Welcome to Me (2014) HD 1080p","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261819205","owner":"29673297@N08","secret":"361c4690c8","server":"4426","farm":5,"title":"_MG_9816.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261819415","owner":"148587713@N04","secret":"8961573f20","server":"4380","farm":5,"title":"Shinju18 shinju18","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261821285","owner":"159267115@N07","secret":"dd05f84e52","server":"4426","farm":5,"title":"Instalaciones de arte espacio publico. U Tadeo - Bogotá","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261821295","owner":"150942186@N03","secret":"daf7cdde78","server":"4406","farm":5,"title":"BLUE CORAL","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261821895","owner":"123775946@N04","secret":"612c9da1c8","server":"4375","farm":5,"title":"Flores","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261822635","owner":"159317345@N05","secret":"ffc142b791","server":"4395","farm":5,"title":"IMG_6656-Edit.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261823975","owner":"91798495@N05","secret":"4b82293499","server":"4439","farm":5,"title":"Joshua Kane lfw 17 #jkfantasy #joshuakane Photographed by Eden Edenski . . . .  #fashionblog #londonfashion #fashionphotographer #catwalk #runway #londonfashionweek #lfw2017 #vogue #fashion #lfw #model #photographer #photography #londonmodel #edenedenski","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261823995","owner":"75818525@N03","secret":"763f48e865","server":"4362","farm":5,"title":"59bd3de0ecb40.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261824135","owner":"100655050@N05","secret":"d34e17aa32","server":"4345","farm":5,"title":"Bei Bei 9:26/","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261824905","owner":"47110204@N07","secret":"eaef29eb0c","server":"4351","farm":5,"title":"IMG_0601.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261825255","owner":"153972702@N04","secret":"6ec1f0db58","server":"4392","farm":5,"title":"Fall Fashion Outfits for Fall : Cute looks for fall and winter!  best boots sweaters and skinny jeans to pull of...","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261825265","owner":"145666613@N06","secret":"0fecc2472c","server":"4392","farm":5,"title":"_DSC2616.jpg","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261826255","owner":"140703604@N07","secret":"6b3a29af89","server":"4352","farm":5,"title":"2017-09-16_05-07-48","ispublic":1,"isfriend":0,"isfamily":0},{"id":"37261826795","owner":"150716329@N04","secret":"b1150d4663","server":"4411","farm":5,"title":"","ispublic":1,"isfriend":0,"isfamily":0}]
         */

        private int page;
        private int pages;
        private int perpage;
        private int total;
        private List<PhotoBean> photo;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public static class PhotoBean {
            /**
             * id : 36423853254
             * owner : 158940018@N08
             * secret : cec4f04862
             * server : 4346
             * farm : 5
             * title : What's app images
             * ispublic : 1
             * isfriend : 0
             * isfamily : 0
             */

            private String id;
            private String owner;
            private String secret;
            private String server;
            private int farm;
            private String title;
            private int ispublic;
            private int isfriend;
            private int isfamily;
            private String url_s;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public String getServer() {
                return server;
            }

            public void setServer(String server) {
                this.server = server;
            }

            public int getFarm() {
                return farm;
            }

            public void setFarm(int farm) {
                this.farm = farm;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIspublic() {
                return ispublic;
            }

            public void setIspublic(int ispublic) {
                this.ispublic = ispublic;
            }

            public int getIsfriend() {
                return isfriend;
            }

            public void setIsfriend(int isfriend) {
                this.isfriend = isfriend;
            }

            public int getIsfamily() {
                return isfamily;
            }

            public void setIsfamily(int isfamily) {
                this.isfamily = isfamily;
            }


            public String getUrl_s() {
                return url_s;
            }

            public void setUrl_s(String url_s) {
                this.url_s = url_s;
            }

        }
    }
}
