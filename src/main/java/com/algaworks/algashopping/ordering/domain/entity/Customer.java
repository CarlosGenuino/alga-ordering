package com.algaworks.algashopping.ordering.domain.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String document;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private Integer loyaltyPoints;

    public Customer(UUID id, String fullName,
                    LocalDate birthDate, String email,
                    String phone, String document,
                    Boolean promotionNotificationsAllowed,
                    Boolean archived, OffsetDateTime registeredAt,
                    OffsetDateTime archivedAt, Integer loyaltyPoints) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
        this.archived = archived;
        this.registeredAt = registeredAt;
        this.archivedAt = archivedAt;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(UUID id, String fullName,
                    LocalDate birthDate, String email,
                    String phone, String document,
                    Boolean promotionNotificationsAllowed,
                    OffsetDateTime registeredAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.registeredAt = registeredAt;
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
    }

    public void addLoyaltyPoints(Integer points){
        Objects.requireNonNull(points);
        if (loyaltyPoints == null){
            loyaltyPoints = 0;
        }
        Integer totalLoyaltyPoints = this.loyaltyPoints + points;
        this.setLoyaltyPoints(totalLoyaltyPoints);
    }

    public void changeFullName(String fullName){
        this.setFullName(fullName);
    }

    public void changeEmail(String email){
        this.setEmail(email);
    }

    public void changePhone(String phone){
        this.setPhone(phone);
    }

    public void archive(){
        this.setArchived(Boolean.TRUE);
        this.setArchivedAt(OffsetDateTime.now());
    }

    private void enablePromotionNotifications(){
        this.setPromotionNotificationsAllowed(Boolean.TRUE);
    }

    private void disablePromotionNotifications(){
        this.setPromotionNotificationsAllowed(Boolean.FALSE);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setFullName(String fullName) {
        Objects.requireNonNull(fullName);
        if (fullName.isEmpty()){
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
        this.fullName = fullName;
    }

    private void setBirthDate(LocalDate birthDate) {
        Objects.requireNonNull(birthDate);
        if (birthDate.isAfter(LocalDate.now())){
            this.birthDate = birthDate;
        }
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setDocument(String document) {
        this.document = document;
    }

    private void setPromotionNotificationsAllowed(Boolean promotionNotificationsAllowed) {
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
    }

    private void setArchived(Boolean archived) {
        this.archived = archived;
    }

    private void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    private void setArchivedAt(OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    private void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public UUID id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String document() {
        return document;
    }

    public Boolean isPromotionNotificationsAllowed() {
        return promotionNotificationsAllowed;
    }

    public Boolean isArchived() {
        return archived;
    }

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    public OffsetDateTime archivedAt() {
        return archivedAt;
    }

    public Integer loyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
