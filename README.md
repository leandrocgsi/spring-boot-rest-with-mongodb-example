# Spring boot REST with MongoDB - Example Project

[![Build Status](https://travis-ci.org/leandrocgsi/spring-boot-rest-with-mongodb-example.svg?branch=master)](https://travis-ci.org/leandrocgsi/spring-boot-rest-with-mongodb-example)
[![Build Status](https://circleci.com/gh/leandrocgsi/spring-boot-rest-with-mongodb-example.svg?&style=shield)](https://circleci.com/gh/leandrocgsi/spring-boot-rest-with-mongodb-example)

# HATEOAS Level 3 in [Richardson Maturity Model](http://martinfowler.com/articles/richardsonMaturityModel.html)

When your app adopt HATEOAS (Hypermedia as the Engine of Application State) according with [Richardson Maturity Model](http://martinfowler.com/articles/richardsonMaturityModel.html) this the highest level, uses HATEOAS to deal with discovering the possibilities of your API towards the clients. 

# Em português

* [Entendendo HATEOAS](http://www.semeru.com.br/blog/entendendo_hateoas/)
* [Aplicações RESTful HATEOAS com SpringBoot](http://www.semeru.com.br/blog/aplicacoes-restfull-hateoas-com-springboot/)


# How to get this project

You can execute following command or as you prefer [download zip here](https://github.com/leandrocgsi/spring-boot-mongodb-example/archive/master.zip) and import in your favourite IDE.

```sh
git clone https://github.com/leandrocgsi/spring-boot-mongodb-example.git
```

# HATEOAS

[HATEOAS (Hypermedia as the Engine of Application State)](https://spring.io/understanding/HATEOAS) is a constraint of the REST application architecture. A hypermedia-driven site provides information to navigate the site's REST interfaces dynamically by including hypermedia links with the responses. This capability differs from that of SOA-based systems and WSDL-driven interfaces. With SOA, servers and clients usually must access a fixed specification that might be staged somewhere else on the website, on another website, or perhaps distributed by email. According with [Richardson maturity model](http://martinfowler.com/articles/richardsonMaturityModel.html) [HATEOAS](https://spring.io/understanding/HATEOAS) is final level of REST. One API is really [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer) when implements this standart. Access following address and see the magic.

```bash
	http://localhost:8081/erudio-restful-api/person/findAll
```

```json
[  
    {  
        "idPerson":1,
        "firstName":"Person Name 1",
        "lastName":"Last Name 1",
        "address":"Some Address in Brasil 1",
        "links":[  
            {  
                "rel":"self",
                "href":"http://localhost:8081/erudio-restful-api/person/1"
            }
        ]
    },
    {  
        "idPerson":2,
        "firstName":"Person Name 2",
        "lastName":"Last Name 2",
        "address":"Some Address in Brasil 2",
        "links":[  
            {  
                "rel":"self",
                "href":"http://localhost:8081/erudio-restful-api/person/2"
            }
        ]
    }
]
```

# Se especialize

[<img src="https://github.com/leandrocgsi/SpringBootPlayground/blob/master/Images/banner_blog_udemy_course_sring_boot.jpg?raw=true">](https://www.udemy.com/restful-apis-do-0-a-nuvem-com-springboot-e-docker/?couponCode=GTHB_REPOS_SALE2019)
