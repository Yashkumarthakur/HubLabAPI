## Common for all micro-services


API 1 : /userrepo
	output : list of all repository for user of GitHub as well as GitLab.
	
	Parameter: "owenership" - optional boolean type.
		Use: true -> display only user's repository.
			  false -> display all accessible repository.
			  
			  
----------------------------------------------------------------------------------------------------------------------------

Sample Test:

			URL : http://localhost:9090/userrepos
			OUTPUT:
			{
    "GitHub": [
        {
            "fullName": "\"CoditasTest/hello-world\"",
            "htmlURL": "\"https://github.com/CoditasTest/hello-world\"",
            "ownerName": "\"CoditasTest\""
        },
        {
            "fullName": "\"CoditasTest/Test1\"",
            "htmlURL": "\"https://github.com/CoditasTest/Test1\"",
            "ownerName": "\"CoditasTest\""
        },
        {
            "fullName": "\"CoditasTest/Test2\"",
            "htmlURL": "\"https://github.com/CoditasTest/Test2\"",
            "ownerName": "\"CoditasTest\""
        },
        {
            "fullName": "\"Yashkumarthakur/HubLabAPI\"",
            "htmlURL": "\"https://github.com/Yashkumarthakur/HubLabAPI\"",
            "ownerName": "\"Yashkumarthakur\""
        },
        {
            "fullName": "\"Yashkumarthakur/JavaFileLoaders\"",
            "htmlURL": "\"https://github.com/Yashkumarthakur/JavaFileLoaders\"",
            "ownerName": "\"Yashkumarthakur\""
        }
    ],
    "GitLab": [
        {
            "fullName": "\"CoditasTest/gitlabtest2\"",
            "htmlURL": "\"https://gitlab.com/CoditasTest/gitlabtest2\"",
            "ownerName": "\"Yashkumar\""
        },
        {
            "fullName": "\"CoditasTest/gitlabtest1\"",
            "htmlURL": "\"https://gitlab.com/CoditasTest/gitlabtest1\"",
            "ownerName": "\"Yashkumar\""
        }
    ]
}





	URL : http://localhost:9090/userrepos?owenership=true
	OUTPUT:
	
		{
    "GitHub": [
        {
            "fullName": "\"CoditasTest/hello-world\"",
            "htmlURL": "\"https://github.com/CoditasTest/hello-world\"",
            "ownerName": "\"CoditasTest\""
        },
        {
            "fullName": "\"CoditasTest/Test1\"",
            "htmlURL": "\"https://github.com/CoditasTest/Test1\"",
            "ownerName": "\"CoditasTest\""
        },
        {
            "fullName": "\"CoditasTest/Test2\"",
            "htmlURL": "\"https://github.com/CoditasTest/Test2\"",
            "ownerName": "\"CoditasTest\""
        }
    ],
    "GitLab": [
        {
            "fullName": "\"CoditasTest/gitlabtest2\"",
            "htmlURL": "\"https://gitlab.com/CoditasTest/gitlabtest2\"",
            "ownerName": "\"Yashkumar\""
        },
        {
            "fullName": "\"CoditasTest/gitlabtest1\"",
            "htmlURL": "\"https://gitlab.com/CoditasTest/gitlabtest1\"",
            "ownerName": "\"Yashkumar\""
        }
    ]
}
			
			  
			  
			  

			  
