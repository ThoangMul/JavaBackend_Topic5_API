package com.example.demo.jwt;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    public Long getId() {
        return id; }

    public String getUsername() {
        return username; }

    public void setUsername(String u) {
        this.username = u; }

    public String getPasswordHash() {
        return passwordHash; }

    public void setPasswordHash(String h) {
        this.passwordHash = h; }

    public Role getRole() {
        return role; }

    public void setRole(Role r) {
        this.role = r; }
}

