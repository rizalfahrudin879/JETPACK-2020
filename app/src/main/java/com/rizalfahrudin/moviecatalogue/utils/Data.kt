package com.rizalfahrudin.moviecatalogue.utils

import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.data.MovieTvEntity

object Data {
    fun generateDataMovie(): List<MovieTvEntity> {
        val movies = ArrayList<MovieTvEntity>()
        movies.add(MovieTvEntity(
            1,
            "A Star Is Born (2018)",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Allys career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            R.drawable.poster_a_start_is_born)
        )
        movies.add(MovieTvEntity(
            2,
            "Aquaman (2018)",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orms half-human, half-Atlantean brother and true heir to the throne.",
            R.drawable.poster_aquaman)
        )
        movies.add(MovieTvEntity(
            3,
            "Cold Zone (2017)",
            "A storm is coming. No one could predict it. No one can measure it. Now one family must survive it. You cant come in from the cold.",
            R.drawable.poster_cold_persuit)
        )
        movies.add(MovieTvEntity(
            4,
            "Avengers: Infinity War (2018)",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            R.drawable.poster_infinity_war)
        )
        movies.add(MovieTvEntity(
            5,
            "Mary Queen of Scots (2018)",
            "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
            R.drawable.poster_marry_queen)
        )
        movies.add(MovieTvEntity(
            6,
            "Master Z: Ip Man Legacy (2018)",
            "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But its not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
            R.drawable.poster_master_z)
        )
        movies.add(MovieTvEntity(
            7,
            "Mortal Engines (2018)",
            "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            R.drawable.poster_mortal_engines)
        )
        movies.add(MovieTvEntity(
            8,
            "Overlord (2018)",
            "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            R.drawable.poster_overlord)
        )
        movies.add(MovieTvEntity(
            9,
            "Robin Hood (2018)",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            R.drawable.poster_robin_hood)
        )
        movies.add(MovieTvEntity(
            10,
            "Spider-Man: Into the Spider-Verse (2018)",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            R.drawable.poster_spiderman)
        )
        return movies
    }

    fun generateDataTv(): List<MovieTvEntity>{
        val tv = ArrayList<MovieTvEntity>()
        tv.add(MovieTvEntity(
            1,
            "NCIS (2003)",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            R.drawable.poster_ncis)
        )
        tv.add(MovieTvEntity(
            2,
            "Supergirl (2015)",
            "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
            R.drawable.poster_supergirl)
        )
        tv.add(MovieTvEntity(
            3,
            "Shameless (2011)",
            "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Franks not at the bar spending what little money they have, hes passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
            R.drawable.poster_shameless)
        )
        tv.add(MovieTvEntity(
            4,
            "Greys Anatomy (2005)",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            R.drawable.poster_grey_anatomy)
        )
        tv.add(MovieTvEntity(
            5,
            "Gotham (2014)",
            "Everyone knows the name Commissioner Gordon. He is one of the crime worlds greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordons story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the worlds most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            R.drawable.poster_gotham)
        )
        tv.add(MovieTvEntity(
            6,
            "Marvels Iron Fist (2017)",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            R.drawable.poster_iron_fist)
        )
        tv.add(MovieTvEntity(
            7,
            "Riverdale (2017)",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            R.drawable.poster_riverdale)
        )
        tv.add(MovieTvEntity(
            8,
            "Supernatural (2005)",
            "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their 67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
            R.drawable.poster_supernatural)
        )
        tv.add(MovieTvEntity(
            9,
            "Family Guy (1999)",
            "Sick, twisted, politically incorrect and Freakin Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, hes not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            R.drawable.poster_family_guy)
        )
        tv.add(MovieTvEntity(
            10,
            "Game of Thrones (2011)",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Nights Watch, is all that stands between the realms of men and icy horrors beyond.",
            R.drawable.poster_god)
        )
        return tv
    }
}