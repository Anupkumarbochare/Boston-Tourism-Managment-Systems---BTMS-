# Boston Tourism Management System (BTMS)

<div align="center">
  <img src="/BTMS_Final/src/main/resources/images/BTMS.png" alt="BTMS Logo" width="200">
  <h3>Enhancing the Tourism Experience in Boston</h3>
</div>

## 🌆 Project Overview

The Boston Tourism Management System (BTMS) is an integrated platform designed to enhance tourism experiences in Boston by connecting key stakeholders such as attractions, transportation providers, hotels, and event planners. BTMS addresses the fragmented nature of tourism services by providing a centralized system that benefits both tourists and businesses.

## 🌟 Problem Statement

Boston is a city rich in history and culture, making it one of the best places to visit in the United States. However, tourists often face a fragmented experience due to:

- Difficulty finding real-time information about places, events, and transportation
- Lack of an integrated booking system for tickets, accommodation, and tours
- Insufficient information for optimizing travel plans, leading to wasted time and resources
- Poor communication between tourism businesses (event organizers, hotels, transportation providers)

These gaps result in decreased tourist satisfaction and missed revenue opportunities for businesses.

## 🎯 Goals and Objectives

BTMS provides a single integrated platform for key tourism services, ensuring a seamless experience for tourists and streamlined operations for all stakeholders.

### Key Features:

- **One-stop management** of attractions, events, transportation, and hospitality
- **User-friendly dashboards** for tourists and businesses to manage activities
- **Real-time information** on events, transportation schedules, and hospitality availability
- **Enhanced communication** among organizations to coordinate resources
- **Data analytics** to help businesses improve services and forecast trends

## 🏗️ System Architecture

BTMS follows a modular design with the following components:

### Core Components

- **BookingService**: Central hub for managing all tourism-related activities
  - Implements singleton pattern for centralized control
  - Manages specialized initialization for each system module
  - Provides synchronized access for booking services

- **User Management**:
  - Secure authentication with UserCredentialsManager
  - Session handling with dedicated UserSession class
  - Support for multiple organizations and enterprises

- **Event Management**:
  - Professional profile management for event managers
  - Comprehensive event tracking with unique identification
  - Dynamic capacity handling and financial control

- **Tour and Attraction Management**:
  - Tour Guide Manager as central controlling class
  - Comprehensive attraction management (location, description, capacity)
  - Tour scheduling and booking system

- **Hospitality Management**:
  - Hierarchical system with HospitalityProperty as parent class
  - Specialized Hotel and Restaurant child classes
  - Price range and availability management

- **Transportation Management**:
  - Route tracking with unique identifiers
  - Dynamic pricing and capacity management
  - Flexible frequency adjustment

- **Administrative System**:
  - Admin oversees CRUD operations for all modules
  - Data Analysts monitor capacity and revenue metrics
  - Market Researchers analyze booking patterns

## 🚀 Advanced Features

1. **QR Code Integration**
   - Digital ticket generation for attractions and events
   - Real-time booking verification and validation

2. **Interactive Maps**
   - Real-time location tracking
   - Visual representation of points of interest
   - Distance calculation for travel planning

3. **Email System**
   - Automated booking confirmations
   - Event notifications and reminders
   - Email format validation

4. **PDF Generation**
   - Comprehensive itinerary planning documents
   - Event schedules for time management

5. **Attraction Management**
   - Dynamic capacity tracking
   - Integrated rating and review systems
   - Photo gallery support

6. **Weather Integration**
   - Real-time weather updates
   - Display of current conditions in Boston

## 💻 Technology Stack

- **APIs**: Weather API, Maps API, Email Services
- **Additional Libraries**: PDF generation, QR code generation

## 📋 Installation and Setup

```bash
# Clone the repository
git clone https://github.com/Anupkumarbochare/Boston-Tourism-Managment-Systems---BTMS.git

# Navigate to the project directory
cd btms

```

## 📊 System Benefits

- **Enhanced User Experience**: Streamlined interfaces for tourists
- **Improved System Efficiency**: Faster processing and resource utilization
- **Secure Data Management**: Reliable handling of user information
- **Personalized Tourist Services**: Convenient planning and booking
- **Operational Effectiveness**: Improved coordination between stakeholders
