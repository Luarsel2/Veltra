package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// Al extender JpaRepository, Spring nos regala automáticamente los métodos
// save(), findAll(), findById(), delete(), etc. — no hace falta escribirlos.
public interface ContactRepository extends JpaRepository<Contact, Long> {
}