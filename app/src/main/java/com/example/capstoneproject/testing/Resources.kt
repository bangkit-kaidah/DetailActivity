package com.example.github.ui.resource


object Resources {
    private var usernameGithub = arrayOf(
        "JakeWharton",
        "amitshekhariitbhu",
        "romainguy",
        "chrisbanes",
        "tipsy",
        "ravi8x",
        "jasoet",
        "budioktaviyan",
        "hendisantika",
        "sidiqpermana"
    )

    private var nameGithub = arrayOf(
        "Jake Wharton",
        "AMIT SHEKHAR",
        "Romain Guy",
        "Chris Banes",
        "David",
        "Ravi Tamada",
        "Deny Prasetyo",
        "Budi Oktaviyan",
        "Hendi Santika",
        "Sidiq Permana"
    )


    private val companyGithub = arrayOf(
        "Google, Inc.",
        "@MindOrksOpenSource",
        "Google",
        "@google working on @android",
        "Working Group Two",
        "AndroidHive | Droid5",
        "@gojek-engineering",
        "@KotlinID",
        "@JVMDeveloperID @KotlinID @IDDevOps",
        "Nusantara Beta Studio"

    )
    private val locationGithub = arrayOf(
        "Pittsburgh, PA, USA",
        "New Delhi, India",
        "California",
        "Sydney, Australia",
        "Trondheim, Norway",
        "India",
        "Kotagede, Yogyakarta, Indonesia",
        "Jakarta, Indonesia",
        "Bojongsoang - Bandung Jawa Barat",
        "Jakarta Indonesia"

    )
    private val repositoryGithub = arrayOf(
        "Repository\n\n" +
                "102",
        "Repository\n\n" +
                "37",
        "Repository\n\n" +
                "9",
        "Repository\n\n" +
                "30",
        "Repository\n\n" +
                "56",
        "Repository\n\n" +
                "28",
        "Repository\n\n" +
                "44",
        "Repository\n\n" +
                "110",
        "Repository\n\n" +
                "1064",
        "Repository\n\n" +
                "65"
    )
    private val followerGithub = arrayOf(
        "Follower\n\n" +
                "56995",
        "Follower\n\n" +
                "5153",
        "Follower\n\n" +
                "7972",
        "Follower\n\n" +
                "14725",
        "Follower\n\n" +
                "788",
        "Follower\n\n" +
                "18628",
        "Follower\n\n" +
                "277",
        "Follower\n\n" +
                "178",
        "Follower\n\n" +
                "428",
        "Follower\n\n" +
                "465"
    )
    private val followingGithub = arrayOf(
        "Following\n\n" +
                "12",
        "Following\n\n" +
                "2",
        "Following\n\n" +
                "0",
        "Following\n\n" +
                "1",
        "Following\n\n" +
                "0",
        "Following\n\n" +
                "3",
        "Following\n\n" +
                "39",
        "Following\n\n" +
                "23",
        "Following\n\n" +
                "61",
        "Following\n\n" +
                "10"
    )

    fun phone(): ArrayList<Github> {
        val list = arrayListOf<Github>()
        for (posisi in nameGithub.indices) {
            val github = Github()
            github.username = usernameGithub[posisi]
            github.name = nameGithub[posisi]
            github.company = companyGithub[posisi]
            github.location = locationGithub[posisi]
            github.repository = repositoryGithub[posisi]
            github.follower = followerGithub[posisi]
            github.following = followingGithub[posisi]
            list.add(github)
        }
        return list
    }

}
