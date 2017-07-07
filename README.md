# mpp-project-library-management

A project for MPP course 

Requirement:

Library Problem Statement
Create the first iteration of a system that a librarian can use to check out books for library members, and that an administrator can use to add new books to the collection, create new library members, and edit library member information.
Most books may be borrowed for 21 days, but some books only for 7 days.
All members of the library are assigned a unique member number. First and last names, address (street, city, state, zip) and phone number of every member are also stored as member data.
Books have a title, ISBN number, list of authors, and availability. Authors have first and last names, address (street, city, state, zip), phone number, credentials, and a short bio.
The library has multiple copies for some popular books. Every copy of a book has its own unique copy number. (Note: A “copy” of a book is an instance of the book. For every book in a library, there is at least one copy; for popular books, there may be more than one copy. In this context, a “copy” of a book is not a reproduction of an original; this is a different meaning of “copy,” not used here.)
Also, for each library member, the system will keep a record of his/her checkout activities in a checkout record. A checkout record consists of a collection of checkout entries. Each entry records each item checked out, the date of checkout, and the due date. The checkout record for a library member is therefore a complete record of every book that the member has ever checked out. We expect that in later phases of the library system, the checkout record will also include a record of fines for later returns and dates paid.
In order to access the system, a librarian or administrator must login. Administrators are able to add/edit member info and add books to the collection, but they are not allowed to checkout books for a member (unless they also have Librarian access). Librarians are allowed to checkout books but not allowed to add/edit members or add books (unless they also have Administrator access).
